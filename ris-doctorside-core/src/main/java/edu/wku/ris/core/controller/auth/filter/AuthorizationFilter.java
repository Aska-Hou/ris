package edu.wku.ris.core.controller.auth.filter;

import edu.wku.ris.common.pojo.exception.BusinessException;
import edu.wku.ris.common.pojo.response.ResponseEnum;
import edu.wku.ris.core.adapter.FilterPathAdapter;
import edu.wku.ris.core.config.AuthThreadLocal;
import edu.wku.ris.core.config.invocation.TokenServiceMockProxy;
import edu.wku.ris.core.pojo.bo.DoctorLogInBO;
import edu.wku.ris.core.service.TokenService;
import edu.wku.ris.core.utils.RedisKeyUtils;
import edu.wku.ris.core.utils.RedisTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/25 19:32
 */
@Component
@Slf4j
public class AuthorizationFilter implements Filter {

    @Autowired
    public FilterPathAdapter filterPathAdapter;

    @Autowired
    public TokenService tokenService;

    @Value("${mock.token:'default'}")
    private String mockToken;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // 不需要过滤的白名单url
        String requestURI = httpServletRequest.getRequestURI();
        List<String> filterPath = filterPathAdapter.getFilterPath();

        for (String path: filterPath){
            if (requestURI.contains(path)){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        // 其他操作都需要过滤
        String token = httpServletRequest.getHeader("token");
        if (token == null){
            httpServletResponse.setStatus(403);
            return;
        }

        TokenService proxyTokenService = TokenServiceMockProxy.getProxy(tokenService, mockToken);

        boolean exist = proxyTokenService.checkToken(token);

        if (!exist){
            httpServletResponse.setStatus(403);
            return;
        }

        String accountTokenKey = RedisKeyUtils.getAccountTokenKey(token);

        try {
            DoctorLogInBO doctorLogInBO = RedisTemplateUtils.getObjectInCache(accountTokenKey);
            // token有效 刷新令牌时间，延长登录
            RedisTemplateUtils.expireObjectInCache(accountTokenKey, RedisKeyUtils.GENERAL_TOKEN_EXPIRE_TIME);

            AuthThreadLocal.setDoctor(doctorLogInBO);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception exception){
            log.error("Filter Chain Executing Error");
            throw new BusinessException(ResponseEnum.INNER_SERVER_ERROR);
        } finally {
            AuthThreadLocal.clean();
        }


    }


}

package edu.wku.ris.common.pojo.exception;

import edu.wku.ris.common.pojo.response.ResponseEnum;
import edu.wku.ris.common.pojo.response.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/26 21:15
 */
@RestControllerAdvice
public class UnifiedExceptionHandler {

    public static final Logger logger = LoggerFactory.getLogger(UnifiedExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseVO handleException(Exception exception) {
        logger.error(exception.getMessage(), exception);
        return ResponseVO.error("服务器内部未知错误");
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseVO handleException(BusinessException businessException) {
        logger.error(businessException.getMessage(), businessException);
        return ResponseVO.error(businessException.getMessage(),businessException.getCode());
    }

    /**
     * Controller上一层相关异常
     */
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MethodArgumentNotValidException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    public ResponseVO handleServletException(Exception e) {
        logger.error(e.getMessage(), e);
        //SERVLET_ERROR(-102, "servlet请求异常"),
        return ResponseVO.error(ResponseEnum.SERVLET_ERROR.getMessage(), ResponseEnum.SERVLET_ERROR.getCode());
    }

}

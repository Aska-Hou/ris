package edu.wku.ris.core.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/25 19:54
 */
public class DefaultFilterPathAdapter implements FilterPathAdapter{

    public static final String DEFAULT_PATH = "/*";

    public static final String LOG_PATH = "/auth/student/log";


    public static final String TOKEN_FUNCTION_PATH = "token";

    public static final String[] SWAGGER_FACTORS = {"/swagger-resources", "/webjars", "/v2", "/swagger-ui.html"};

    @Override
    public List<String> getDefaultPath() {
        return Collections.singletonList(DEFAULT_PATH);
    }

    @Override
    public List<String> getFilterPath(String... path) {
        List<String> pathList = new ArrayList<>();
        if (path.length != 0) {
            pathList.addAll(Arrays.asList(path));
        }
        pathList.add(LOG_PATH);
        pathList.add(TOKEN_FUNCTION_PATH);
        pathList.addAll(Arrays.asList(SWAGGER_FACTORS));
        return pathList;
    }
}

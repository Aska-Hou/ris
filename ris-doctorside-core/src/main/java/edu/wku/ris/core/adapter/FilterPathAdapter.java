package edu.wku.ris.core.adapter;

import java.util.List;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/25 19:52
 */
public interface FilterPathAdapter {

    List<String> getDefaultPath();

    List<String> getFilterPath(String... path);
}

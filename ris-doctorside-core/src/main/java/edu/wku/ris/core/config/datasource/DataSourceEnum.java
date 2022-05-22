package edu.wku.ris.core.config.datasource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/30 20:38
 */
@Getter
@AllArgsConstructor
public enum DataSourceEnum {

    MASTER("master"),
    SLAVE("slave");

    private String value;

    @Override
    public String toString() {
        return "DataSourceEnum{" +
                "value='" + value + '\'' +
                '}';
    }

}

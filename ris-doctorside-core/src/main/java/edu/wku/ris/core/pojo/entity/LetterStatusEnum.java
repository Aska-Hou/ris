package edu.wku.ris.core.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/03/02 13:03
 */
@AllArgsConstructor
@Getter
public enum LetterStatusEnum {

    SUBMITTED("submitted"),

    ACCEPTED("accepted"),

    WRITING("writing"),

    FINISHED("finished"),

    END("end");

    public String value;


    @Override
    public String toString() {
        return super.toString();
    }
}

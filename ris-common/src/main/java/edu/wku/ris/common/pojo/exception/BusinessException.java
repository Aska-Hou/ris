package edu.wku.ris.common.pojo.exception;

import edu.wku.ris.common.pojo.response.ResponseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/25 22:03
 */
@Data
@NoArgsConstructor
public class BusinessException extends RuntimeException{

    private int code;
    private String message;

    public BusinessException(int code) {
        this.code = code;
    }

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException(Throwable cause, int code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public BusinessException(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    public BusinessException(ResponseEnum responseEnum, Throwable cause) {
        super(cause);
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }
}

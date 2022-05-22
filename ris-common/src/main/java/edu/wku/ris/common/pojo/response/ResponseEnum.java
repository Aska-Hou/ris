package edu.wku.ris.common.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS(0, "success"),

    ERROR(-1, "internel server error"),

    //-1xx 服务器错误
    BAD_SQL_GRAMMAR_ERROR(-101, "sql语法错误"),
    SERVLET_ERROR(-102, "servlet请求异常"),
    UPLOAD_ERROR(-103, "文件上传错误"),
    EXPORT_DATA_ERROR(104, "数据导出失败"),


    //-2xx 参数校验
    TOKEN_INVALID_ERROR(-203, "Invalid Token"),
    PASSWORD_NULL_ERROR(204, "Password cannot be Null"),
    EMAIL_NULL_ERROR(205, "Email cannot be Null"),
    LOGIN_ERROR(209, "Account or Password is incorrect"),


    // -3xx
    STUDENT_INFO_EMPTY_ERROR(302, "Student Info is Empty."),


    // 4xx

    // 5xx
    INNER_SERVER_ERROR(-501, "Inner Server Error"),
    REDIS_NOT_AVAILABLE(-503, "Account Operation Error. Redis Service is not Available."),//其他失败
    ;

    private Integer code;
    private String message;

    @Override
    public String toString() {
        return "ResponseEnum{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}

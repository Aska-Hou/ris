package edu.wku.ris.common.pojo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@ApiModel(description="统一返回VO模型")
public class ResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "状态码")
    private Integer code;

    @ApiModelProperty(name = "用户展示信息")
    private String message;

    @ApiModelProperty(name = "Data Map形式")
    private Map<String, Object> data = new HashMap<>();

    private ResponseVO(){

    }

    public static ResponseVO success(){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setCode(ResponseEnum.SUCCESS.getCode());
        responseVO.setMessage(ResponseEnum.SUCCESS.getMessage());
        return responseVO;
    }

    public static ResponseVO success(String message){
        ResponseVO responseVO = success();
        responseVO.setMessage(message);
        return responseVO;
    }

    public ResponseVO data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public static ResponseVO error(){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setCode(ResponseEnum.ERROR.getCode());
        responseVO.setMessage(ResponseEnum.ERROR.getMessage());
        return responseVO;
    }

    public static ResponseVO error(String message){
        ResponseVO responseVO = error();
        responseVO.setMessage(message);
        return responseVO;
    }

    public static ResponseVO error(String message, int code){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setCode(code);
        responseVO.setMessage(message);
        return responseVO;
    }

    public static ResponseVO setResult(ResponseEnum responseEnum){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setCode(responseEnum.getCode());
        responseVO.setMessage(responseEnum.getMessage());
        return responseVO;
    }

}

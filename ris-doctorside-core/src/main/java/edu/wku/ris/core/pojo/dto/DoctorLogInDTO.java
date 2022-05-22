package edu.wku.ris.core.pojo.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/26 20:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description=" PHD登录 前端提交对象 email, password 必须字段")
@AllArgsConstructor
@NoArgsConstructor
public class DoctorLogInDTO {

    private static final long serialVersionUID = 1L;

    private String email;

    private String password;



}

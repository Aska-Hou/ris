package edu.wku.ris.core.pojo.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/26 20:48
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description="PHD登出 前端提交对象  直接传token退出")
@AllArgsConstructor
@NoArgsConstructor
public class DoctorLogOutDTO {

    private String token;

}

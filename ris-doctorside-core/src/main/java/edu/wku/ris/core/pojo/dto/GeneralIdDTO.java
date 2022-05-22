package edu.wku.ris.core.pojo.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/27 11:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description="统一id dto")
@AllArgsConstructor
@NoArgsConstructor
public class GeneralIdDTO {

    private Long id;

    private String stringId;

    private String description;

    public GeneralIdDTO(Long id){
        this.id = id;
    }
}

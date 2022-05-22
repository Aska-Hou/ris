package edu.wku.ris.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/5/2 12:59
 */

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Recommendation Letter Form", description="")
public class RecommendationLetterForm {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long studentId;

    private Long doctorId;

    private Date ddl;

    private String link;

    private String statement;

    private String letterStatus;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

}

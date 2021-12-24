package com.Httb.bookManage.mbg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * firstitemsysteminfo
 * @author 
 */
@ApiModel(value="com.Httb.bookManage.mbg.entity.Firstitemsysteminfo")
@Data
public class Firstitemsysteminfo implements Serializable {
    private Integer wid;

    private String code;

    private String chinese;

    private String english;

    private String fanti;

    private String infodate;

    private Integer mid;

    private static final long serialVersionUID = 1L;
}
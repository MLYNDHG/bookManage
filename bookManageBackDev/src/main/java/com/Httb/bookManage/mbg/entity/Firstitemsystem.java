package com.Httb.bookManage.mbg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * firstitemsystem
 * @author 
 */
@ApiModel(value="com.Httb.bookManage.mbg.entity.Firstitemsystem")
@Data
public class Firstitemsystem implements Serializable {
    private Integer mid;

    private String systemname;

    private String systemcode;

    private static final long serialVersionUID = 1L;
}
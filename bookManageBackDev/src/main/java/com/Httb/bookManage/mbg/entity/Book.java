package com.Httb.bookManage.mbg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * book
 * @author 
 */
@ApiModel(value="com.Httb.bookManage.mbg.entity.Book")
@Data
public class Book implements Serializable {
    /**
     * 图书id
     */
    @ApiModelProperty(value="图书id")
    private Integer id;

    /**
     * 图书名称
     */
    @ApiModelProperty(value="图书名称")
    private String name;

    /**
     * 图书描述
     */
    @ApiModelProperty(value="图书描述")
    private String description;

    /**
     * 图书作者
     */
    @ApiModelProperty(value="图书作者")
    private String author;

    /**
     * 图书状态
     */
    @ApiModelProperty(value="图书状态")
    private Boolean status;

    /**
     * 图书借阅状态
     */
    @ApiModelProperty(value="图书借阅状态")
    private Boolean log;

    /**
     * 图书添加时间
     */
    @ApiModelProperty(value="图书添加时间")
    private Date time;

    private static final long serialVersionUID = 1L;
}
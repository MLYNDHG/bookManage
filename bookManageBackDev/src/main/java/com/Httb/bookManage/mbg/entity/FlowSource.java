package com.Httb.bookManage.mbg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * flow_source
 * @author 
 */
@ApiModel(value="com.Httb.bookManage.mbg.entity.FlowSource")
@Data
public class FlowSource implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty(value="主键id")
    private Integer id;

    /**
     * 流控id
     */
    @ApiModelProperty(value="流控id")
    private Integer fid;

    /**
     * 源列表id
     */
    @ApiModelProperty(value="源列表id")
    private Integer sid;

    /**
     * 是否删除
     */
    @ApiModelProperty(value="是否删除")
    private Boolean status;

    /**
     * 添加时间
     */
    @ApiModelProperty(value="添加时间")
    private Date time;

    private static final long serialVersionUID = 1L;
}
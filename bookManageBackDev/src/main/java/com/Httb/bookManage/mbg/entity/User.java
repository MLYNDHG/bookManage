package com.Httb.bookManage.mbg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * user
 * @author 
 */
@ApiModel(value="com.Httb.finallytest.mbg.entity.User")
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    @ApiModelProperty(value="用户id")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名")
    private String username;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value="用户昵称")
    private String nickname;

    /**
     * 用户密码
     */
    @ApiModelProperty(value="用户密码")
    private String password;

    /**
     * 用户头像
     */
    @ApiModelProperty(value="用户头像")
    private String head;

    /**
     * 用户电话
     */
    @ApiModelProperty(value="用户电话")
    private String tel;

    /**
     * 用户邮箱
     */
    @ApiModelProperty(value="用户邮箱")
    private String email;

    /**
     * 用户状态
     */
    @ApiModelProperty(value="用户状态")
    private Boolean status;

    /**
     * 是否为超级用户
     */
    @ApiModelProperty(value="是否为超级用户")
    private Boolean isSuper;

    private static final long serialVersionUID = 1L;
}
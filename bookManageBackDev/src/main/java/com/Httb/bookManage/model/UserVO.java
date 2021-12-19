package com.Httb.bookManage.model;

import com.Httb.bookManage.mbg.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserVO extends User {

    @ApiModelProperty("新密码")
    private String newPassword;
}

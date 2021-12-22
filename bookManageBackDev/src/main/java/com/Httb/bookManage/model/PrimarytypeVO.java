package com.Httb.bookManage.model;

import com.Httb.bookManage.mbg.entity.Primarytype;
import com.Httb.bookManage.mbg.entity.Secondarytype;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PrimarytypeVO extends Primarytype {

    /**
     * 一级类型中的二级类型列表
     */
    private List<Secondarytype> sList;
}

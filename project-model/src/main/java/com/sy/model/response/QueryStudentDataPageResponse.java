package com.sy.model.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: shenyang
 * @Date: 2020/1/19 13:58
 */
@Setter
@Getter
public class QueryStudentDataPageResponse implements Serializable {

    private Long id;

    /**
     * 姓名
     */
    private String name;


}

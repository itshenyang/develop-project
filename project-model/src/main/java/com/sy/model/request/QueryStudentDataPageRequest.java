package com.sy.model.request;

import com.sy.model.page.Pager;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: shenyang
 * @Date: 2020/1/19 13:43
 */
@Setter
@Getter
public class QueryStudentDataPageRequest extends Pager implements Serializable {
    /**
     * 关键字
     */
    private String keyword;
}

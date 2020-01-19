package com.sy.model.page;


import com.sy.model.enums.DirectionEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 分页类
 *
 * @author shenyang
 * @date 2020/1/19
 * * @param null
 * @return
 */
@Setter
@Getter
public class Pager<T> implements Serializable {

    public Pager() {
        pageSize = 20;
        pageNo = 1;
        totalPages = 0;
        direction = DirectionEnum.DESC;
    }

    /**
     * 总行
     */
    private int totalRows;

    /**
     * 每页显示的行
     */
    private int pageSize;

    /**
     * 当前页号
     */
    private int pageNo;

    /**
     * 总页
     */
    private int totalPages;


    private List<T> list;

    /**
     * 通用查寻参数asc desc
     */
    private DirectionEnum direction;

    /**
     * 排序的字
     */
    private String sort;


}

package com.sy.service;


import com.sy.model.page.Pager;
import tk.mybatis.mapper.entity.Example;

/**
 * @Author: shenyang
 * @Date: 2020/1/10 17:28
 */
public interface CommonService<T> {

    T selectTById(Object id);


    Boolean updateTById(T t);


    Boolean insertT(T t);


    Boolean deleteTById(Object id);

    Pager<T> getPager(Pager pager, Example example) throws Exception;
}

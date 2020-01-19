package com.sy.service.impl;

import com.sy.model.enums.DirectionEnum;
import com.sy.model.page.Pager;
import com.sy.model.page.ToolPaging;
import com.sy.service.CommonService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 基础服务实现
 *
 * @author lichengyi
 * @version 1.0
 * @date 2018-07-04 10:29
 **/
public class CommonServiceImpl<T> implements CommonService<T> {

    @Autowired
    protected Mapper<T> mapper;


    @Override
    public T selectTById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean updateTById(T t) {
        return mapper.updateByPrimaryKeySelective(t) == 1;
    }

    @Override
    public Boolean insertT(T t) {
        return mapper.insertSelective(t) == 1;
    }

    @Override
    public Boolean deleteTById(Object id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public Pager<T> getPager(Pager pager, Example example) throws Exception {
        if (!pager.getDirection().equals(DirectionEnum.ASC)) {
            pager.setDirection(DirectionEnum.DESC);
        }
        pager.setPageNo(pager.getPageNo() <= 0 ? 1 : pager.getPageNo());
        pager.setPageSize(pager.getPageSize() <= 0 ? 10 : pager.getPageSize());

        //排序
        try {
            if (pager.getDirection().equals(DirectionEnum.ASC)) {
                example.orderBy(pager.getSort()).asc();
            } else {
                example.orderBy(pager.getSort()).desc();
            }
        } catch (Exception ex) {
            throw new Exception(ex + "排序字段有错误");
        }

        //查询总个数
        int totalRows = mapper.selectCountByExample(example);
        pager.setTotalRows(totalRows);
        pager.setTotalPages(ToolPaging.howManyPage(totalRows, pager.getPageSize()));
        int offset = (pager.getPageNo() - 1) * pager.getPageSize();
        int limit = pager.getPageSize();
        RowBounds rowBounds = new RowBounds(offset, limit);
        //查询数据
        List<T> list = mapper.selectByExampleAndRowBounds(example, rowBounds);
        pager.setList(list);
        return pager;
    }
}

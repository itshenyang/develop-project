package com.sy.service.impl;

import com.sy.common.utils.Tr;
import com.sy.localhost.entity.Student;
import com.sy.model.page.Pager;
import com.sy.service.StudentService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.MessageFormat;

/**
 * @Author: shenyang
 * @Date: 2020/1/10 15:21
 */
@Service
public class StudentServiceImpl extends CommonServiceImpl<Student> implements StudentService {


    @Override
    public Pager<Student> queryStudentDataPage(String keyword, Pager pager) throws Exception {

        Example example = new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andLike(Tr.fnToName(Student::getName), MessageFormat.format("%{0}%", keyword));

        return super.getPager(pager, example);
    }

}

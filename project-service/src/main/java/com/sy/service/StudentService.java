package com.sy.service;

import com.sy.localhost.entity.Student;
import com.sy.model.page.Pager;

/**
 * @Author: shenyang
 * @Date: 2020/1/10 15:21
 */
public interface StudentService extends CommonService<Student> {

    Pager<Student> queryStudentDataPage(String keyword, Pager pager) throws Exception;

}

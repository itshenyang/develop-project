package com.sy.localhost.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: shenyang
 * @Date: 2020/1/10 15:01
 */
@Getter
@Setter
@Table(name = "student")
public class Student implements Serializable {

    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 姓名
     */
    private String name;

    private static final long serialVersionUID = 1L;


}

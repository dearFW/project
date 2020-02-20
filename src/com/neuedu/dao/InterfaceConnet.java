package com.neuedu.dao;

import com.neuedu.pojo.Student;

import java.util.List;

/**
 * Created by heystephen on 2020/2/18.
 */
public interface InterfaceConnet {
    List<Student> query();
    //差增改删
    int add(Student student);
    int update(Student student);
    int del(int id);
    Student queryOne(int id);
}

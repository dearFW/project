package com.neuedu.sever;

import com.neuedu.dao.Connet;
import com.neuedu.pojo.Student;

import java.util.List;

/**
 * Created by heystephen on 2020/2/18.
 */
    public class Sever implements IterfaceStudent{
    Connet connet = new Connet();
    //串联业务逻辑
    @Override
    public List<Student> query() {
        return connet.query();
    }

    @Override
    public int add(Student student) {
        return connet.add(student);
    }

    @Override
    public int update(Student student) {
        return 0;
    }

    @Override
    public int del(int id) {
        return 0;
    }

    @Override
    public Student queryOne(int id) {
        return null;
    }
}

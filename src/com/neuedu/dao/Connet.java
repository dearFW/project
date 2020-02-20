package com.neuedu.dao;


import com.neuedu.pojo.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by heystephen on 2020/2/18.
 */
//实现数据库增删改查
public class Connet implements InterfaceConnet{
    String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8";
    String username = "root";
    String password = "123456";
    @Override
    public List<Student> query() {
        List<Student> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,username,password);
            pstmt = con.prepareStatement("select id,name,age,gender from student");
            // 查询 调用 executeQuery方法 返回一个 ResultSet 结果集
            rs = pstmt.executeQuery();

           /*  ResultSet 每次调用 next()方法
           *    1   看有没有下一行 如果没有返回fasle
           *    2   如有 将游标推向下一行 返回true
           * */
            while(rs.next()){
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setGender(rs.getInt("gender"));
                list.add(student);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(rs != null)
                    rs.close();
                if(pstmt != null)
                    pstmt.close();
                if(con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public int add(Student student) {
        int i =0;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,username,password);
            pstmt = con.prepareStatement("insert into student(name,age,gender) values(?,?,?)");
            //  查询  则调用 executeQuery方法 返回一个 ResultSet 结果集
           pstmt.setString(1,student.getName());
           pstmt.setInt(1,student.getAge());
           pstmt.setInt(1,student.getGender());
           i = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(pstmt != null)
                    pstmt.close();
                if(con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return i;
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
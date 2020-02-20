package com.neuedu.util;

import com.neuedu.pojo.Dept;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by heystephen on 2020/2/20.
 */
public class JDBCutil {
    private static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

  public   static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    // 封装增删改
    public static int executeUpdate(String sql, Object... params) {
        int result = 0;
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }
        return result;
    }

    // 封装查询
    public static <T> List<T> excuteQuery(String sql, Class<T> clz, Object... params) {
        // List<Student> 没有继承 List<Object>
        List<T> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                // 创建一个T类型的对象
                // 该方法是通过反射 class对象 调用 无参构造来创建对象
                T t = clz.newInstance();
                // 通过 字段名 从 rs中 拿值  然后赋值给 对象的属性
                // 获取有多少个属性
                Field[] fields = clz.getDeclaredFields();
                for (Field f : fields) {
                    // 把属性名当做数据库里的字段名
                    try {
                        // f类在第一次主动使用的时候    会把class加载到 我们内存的方法区中
                        // 并且在堆区中会创建一个class类型的对象
                        //反射就是  我们获取那个class类型的对象
                        // 1 类型.class    Class clz = Student.class;
                        //2 对象名.getClass()  Student student = new Student();
                        // Class clz2 = student.getClass();
                        //3  Class.forName("类的完全限定名")
                        // Class clz3 = Class.forName("com.neuedu.pojo.Student");


                        String column = f.getName();
                        if (f.isAnnotationPresent(Colunm.class)) {
                            // 如果有 要用获取到注解的值 来替换 column的值

                            // 获取注解
                            Colunm c = f.getAnnotation(Colunm.class);
                            column = c.value();
                        }
                        Object value = rs.getObject(column);
                        // 为属性赋值 属性对象是 Field 但是我们必须指明 我们要为哪个对象的属性赋值
                    /*
                        用 field 调用set方法 可以进行赋值
                        注意 如果该属性是 private 那么在赋值之前必须先打开权限
                     */
                        f.setAccessible(true);
                        f.set(t, value);
                    } catch (Exception ex) {
                    }
                }
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }
        return list;
    }

    // 封装通用查询   和List放相同类型的对象
    public static <T> List<T> excuteQuery(String sql, RowMap<T> rowMap, Object... params) {
        List<T> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(sql);
            //  sql语句放循环问号值
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                //  根据对象的属性值  采用不同的处理方式
                T t = rowMap.rowMapping(rs);
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }
        return list;
    }

    // 封装关闭方法
    static void close(Connection con, PreparedStatement pstmt) {
        try {
            if (pstmt != null)
                pstmt.close();
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
            close(con, pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





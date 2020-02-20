package com.neuedu.Test;

import com.neuedu.pojo.Dept;
import com.neuedu.util.JDBCutil;
import com.neuedu.util.RowMap;
import com.neuedu.web.Web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by heystephen on 2020/2/18.
 */
public class Test {
    public static void main(String[] args){
       /* Web web = new Web();
        web.showmain();
        web.input();
        Class clz = Dept.class;
        */
        // 调用 该类的无参构造创建一个对象
        // 类下 每一个方法都会封装成 Method类型的对象
        // 类下 每一个属性都会封装成 Field类型的对象
        // 有多少个属性 就有多少个 Field  有多少个方法 就有多少个 Method
        // 实际上  Field是个数组  Method也是数组
       /* Field[] fields = clz.getDeclaredFields();
        for (Field  f : fields){
            System.out.println(f.getName());
        }*/

        /* List<Dept> list = JDBCutil.executeQuery("select deptno,dname,loc from dept",Dept.class);
        System.out.println(list);*/


       /* List<Dept> list = JDBCutil.excuteQuery("select deptno,dname,loc from dept", new RowMap<Dept>() {
            @Override
            public Dept rowMapping(ResultSet rs) {
                Dept dept = new Dept();
                try {
                    dept.setDeptno(rs.getInt("deptno"));
                    dept.setDname(rs.getString("dname"));
                    dept.setLocal(rs.getString("loc"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return dept;
            }
        });
        System.out.println(list);  */

        Connection con = JDBCutil.getConnection();
        PreparedStatement pstmt = null;
        // 如果实现事务管理  我们就必须 不能让 每次增删改之后都自动提交
        try {
            con.setAutoCommit(false);
            Thread.sleep(1000);
            pstmt = con.prepareStatement("insert into student(id,name,age,gender) values(?,?,?,?)");
            pstmt.setInt(1,1);
            pstmt.setString(2,"张三");
            pstmt.setInt(3,20);
            pstmt.setInt(4,1);
            Thread.sleep(1000);
            int i = pstmt.executeUpdate();
            System.out.println("事务1执行--"+i);

            con.commit();


        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstmt != null)
                    pstmt.close();
                if(con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}

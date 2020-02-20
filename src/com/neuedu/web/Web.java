package com.neuedu.web;

import com.neuedu.pojo.Student;
import com.neuedu.sever.Sever;

import java.util.List;
import java.util.Scanner;

/**
 * Created by heystephen on 2020/2/18.
 */
//用户交互  web层
public class Web {
   Sever studentService = new Sever();
    public void showmain(){
        System.out.println("---------------------------");
        System.out.println("1-----------------------查询");
        System.out.println("2-----------------------添加");
        System.out.println("3-----------------------修改");
        System.out.println("4-----------------------删除");
        System.out.println("其他--------------------退出");
        System.out.println("---------------------------");
    }
    public void input(){
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        if(i==1){
            query();
        }else if(i==2){
           add(scanner);
        }else if(i==3){
            System.out.println("修改");
        }else if(i==4){
            System.out.println("删除");
        }else{
            System.exit(0);
        }
    }
    public void query(){
        // 在此要调用 服务层提供的方法来去访问数据库
        List<Student> list = studentService.query();
        for(Student student : list){
            System.out.println(student);
        }
    }
    public void add( Scanner scanner){
        System.out.println("输入要添加的名字");
        String name = scanner.next();
        System.out.println("输入要添加的年龄");
        int age = scanner.nextInt();
        System.out.println("输入性别  1  男  0  女");
        int gender = scanner.nextInt();
        Student student = new Student(name,age,gender);
        //调用服务层  添加该数据
        studentService.add(student);
        query();
    }
}

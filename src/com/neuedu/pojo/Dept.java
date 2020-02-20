package com.neuedu.pojo;

import com.neuedu.util.Colunm;

/**
 * Created by heystephen on 2020/2/20.
 */
public class Dept {
    private Integer deptno;
    private String dname;
    @Colunm("loc")
    private String local;

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", local='" + local + '\'' +
                '}';
    }
}

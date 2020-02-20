package com.neuedu.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by heystephen on 2020/2/20.
 */

  /*  注解 ：
          *   在反射过程中
          *   为了让 类 属性 方法 等实现某些特定功能 而诞生的
          *   定义一个注解  写法 注解就是 @接口
         *
         * @Target(ElementType.FIELD)    只能加属性上
    @Target(ElementType.TYPE)    只能加类上
    @Target(ElementType.METHOD    只能加方法上
    @Retention(RetentionPolicy.RUNTIME)生命周期   整个程序运行都有效
         *
         * */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Colunm {
    String value();
}

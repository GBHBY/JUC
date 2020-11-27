package com.gyb.demo5;

import java.util.Arrays;
import java.util.List;

/**
 * @author gb
 * @version 1.0
 * description:stream的简单使用
 * @date 2020/11/28 0:17
 */

public class Demo1 {
    public static void main(String[] args) {
        User u1 = new User(23,"e",1);
        User u2 = new User(21,"g",2);
        User u3 = new User(43,"v",3);
        User u4 = new User(54,"q",4);

        List<User> list = Arrays.asList(u1,u2,u3,u4);
        /**
         *filter就是在过滤
         *
         *
         */
        list.stream().filter(x ->{return x.getId()%2==0;}).filter(x ->{return x.getAge()>23;})
                .map(m ->{return m.getName().toUpperCase();}).sorted().forEach(System.out::println);


    }
}

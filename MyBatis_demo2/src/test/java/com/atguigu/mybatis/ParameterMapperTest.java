package com.atguigu.mybatis;

import com.atguigu.mybatis.mapper.ParameterMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterMapperTest {

    /*
           MyBatis获取参数值的两种方式:${} 和 #{}
            ${}本质字符串拼接
            #{}本质占位符赋值

            *MyBatis获取参数值的各种情况:
            1.mapper接口方法的参数为单个的字面量类型
                可以通过${} 和 #{} 以任意的名称获取参数值，但是需要注意${}的单引号问题
            2.mapper接口方法的参数为多个时
                此时MyBatis会将这些参数放在一个map集合中，以两种方式存储
                a>以arg0，arg1...为键，以参数为值
                b>以param1,param2....为键，以参数为值
                因此只需要通过#{}和${}以键的方式访问值即可，但是需要注意${}的单引号问题
            3.若mapper接口方法的参数有多个时，可以将这些参数放在一个map中存储
                因此只需要通过#{}和${}以键的方式访问值即可，但是需要注意${}的单引号问题
            4.mapper接口方法的参数是实体类类型的参数
                因此只需要通过#{}和${}以属性的方式访问属性值即可，但是需要注意${}的单引号问题
            5.使用@Param注解命名参数
                可以通过${} 和 #{} 以任意的名称获取参数值，但是需要注意${}的单引号问题
                a>以@Param注解的值为键，以参数为值
                b>以param1,param2....为键，以参数为值
                因此只需要通过#{}和${}以属性的方式访问属性值即可，但是需要注意${}的单引号问题

     */
    @Test
    public void testCheckLoginByParam() {
        SqlSession session = SqlSessionUtils.getSession();
        ParameterMapper mapper = session.getMapper(ParameterMapper.class);
        User user = mapper.checkLoginByParam("张三","1234");
        System.out.println(user);
    }


    @Test
    public void testInsertUser() {
        SqlSession session = SqlSessionUtils.getSession();
        ParameterMapper mapper = session.getMapper(ParameterMapper.class);
        int i = mapper.insertUser(new User(null, "张三", "123545", 25, "男", "221595945@qq.com"));
        System.out.println(i);
    }
    
    @Test
    public void testInsertsUser(){
        SqlSession session = SqlSessionUtils.getSession();
        long start = System.currentTimeMillis();
        ParameterMapper mapper = session.getMapper(ParameterMapper.class);
        for (int i=0;i<5000;i++){
            mapper.insertsUser(new User(null,"1","2",3,"4","5"));
        }
        session.commit();
        System.out.println(System.currentTimeMillis()-start);
        session.commit();
    }

    @Test
    public void testCheckLoginByMap() {
        SqlSession session = SqlSessionUtils.getSession();
        ParameterMapper mapper = session.getMapper(ParameterMapper.class);
        Map<String,Object> map = new HashMap<>();
        map.put("username","admin");
        map.put("password","123456");
        User user = mapper.checkLoginMap(map);
        System.out.println(user);
    }



    @Test
    public void testCheckLogin() {
        SqlSession session = SqlSessionUtils.getSession();
        ParameterMapper mapper = session.getMapper(ParameterMapper.class);
        User user = mapper.checkLogin("张三","1234");
        System.out.println(user);
    }




    @Test
    public void testGetUserByUsername() {
        SqlSession session = SqlSessionUtils.getSession();
        ParameterMapper mapper = session.getMapper(ParameterMapper.class);
        User user = mapper.getUserByUsername("张三");
        System.out.println(user);
    }


    @Test
    public void testGetAllUser() {
        SqlSession session = SqlSessionUtils.getSession();
        ParameterMapper mapper = session.getMapper(ParameterMapper.class);
        List<User> allUser = mapper.getAllUser();
        allUser.forEach(System.out::println);

    }

    @Test
    public void testJDBC() throws Exception {
        String username = "admin";
        Class.forName("");
        Connection connection = DriverManager.getConnection("", "", "");
//        connection.prepareStatement("select * from t_user where usernmae = ");

        PreparedStatement preparedStatement = connection.prepareStatement("select * from t_user wherer username = ?");
        preparedStatement.setString(1, username);
    }

//    @Test
//    public void testUpdate(){
//        SqlSession session = SqlSessionUtils.getSession();
//        ParameterMapper mapper1 = session.getMapper(ParameterMapper.class);
//        mapper1.updateUser()
//
//    }
}

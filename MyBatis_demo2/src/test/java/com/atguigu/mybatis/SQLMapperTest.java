package com.atguigu.mybatis;

import com.atguigu.mybatis.mapper.SQLMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.*;
import java.util.List;

public class SQLMapperTest {
    @Test
    public void testGetUserByLike(){
        SqlSession session = SqlSessionUtils.getSession();
        SQLMapper mapper = session.getMapper(SQLMapper.class);
        List<User> a = mapper.getUserByLike("男");
        a.forEach(System.out::println);
    }

    @Test
    public void testDeleteMore(){
        SqlSession session = SqlSessionUtils.getSession();
        SQLMapper mapper = session.getMapper(SQLMapper.class);
        int i = mapper.deleteMore("1,2,3");
        System.out.println(i);
    }

    @Test
    public void testGetUserByTableName(){
        SqlSession session = SqlSessionUtils.getSession();
        SQLMapper mapper = session.getMapper(SQLMapper.class);
        List<User> userByIdName = mapper.getUserByIdName("t_user");
        userByIdName.forEach(System.out::println);
    }


    @Test
    public void testJDBC() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis", "root", "yk20010924yk");
        PreparedStatement insert = connection.prepareStatement("insert into t_user values (15,'Tom','456789',45,'男','4562@qq.com')", Statement.RETURN_GENERATED_KEYS);
        insert.executeUpdate();
        ResultSet generatedKeys = insert.getGeneratedKeys();
        System.out.println(generatedKeys);
    }

    @Test
    public void testInsertUser(){
        SqlSession session = SqlSessionUtils.getSession();
        SQLMapper mapper = session.getMapper(SQLMapper.class);
        User user = new User(null, "王五", "123", 23, "男", "12341@qq.com");
        mapper.insertUser(user);
        System.out.println(user);

    }
}

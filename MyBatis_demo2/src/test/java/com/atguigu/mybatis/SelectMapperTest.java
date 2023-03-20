package com.atguigu.mybatis;

import com.atguigu.mybatis.mapper.SelectMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class SelectMapperTest {
    /*
    MyBatis的各种查询功能：
    1.若查询出的数据只有一条，
        可以通过实体类对象
        可以通过list集合接收
        可以通过map集合接收，{password=123456, sex=男, id=3, age=23, email=12345@qq.com, username=admin}

    2.若查询出的数据有多条，
        可以通过list集合接收
        可以通过map类型的list集合接收
        可以在mapper接口的方法上添加@MapKey注解，
            此时可将每条数据转换的map的集合作为值，以某个字段的值作为键，放在同一个map集合
        注意：一定不能通过实体类对象接收，此时会抛出异常
         */

    /*
    MyBatis中设置了默认的类型别名
    java.lang.Integer-->int integer
    int --> _int,_integer
    Map --> map
    String --> string
     */
    @Test
    public void testGeTUserById(){
        SqlSession session = SqlSessionUtils.getSession();
        SelectMapper mapper = session.getMapper(SelectMapper.class);
        List<User> userById = mapper.getUserById(3);
        System.out.println(userById);
    }

    @Test
    public void testGetAllUser(){
        SqlSession session =SqlSessionUtils.getSession();
        SelectMapper mapper = session.getMapper(SelectMapper.class);
        List<User> allUser = mapper.getAllUser();
        System.out.println(allUser);
    }
    @Test
    public void testGetCount(){
        SqlSession session = SqlSessionUtils.getSession();
        SelectMapper mapper = session.getMapper(SelectMapper.class);
        Integer count = mapper.getCount();
        System.out.println(count);
    }

    @Test
    public void testGetByIdToMap(){
        SqlSession session = SqlSessionUtils.getSession();
        SelectMapper mapper = session.getMapper(SelectMapper.class);
        Map<String, Object> userByIdToMap =mapper.getUserByIdToMap(3);
        System.out.println(userByIdToMap);
    }

    @Test
    public void testGetAllToMap(){
        SqlSession session = SqlSessionUtils.getSession();
        SelectMapper mapper = session.getMapper(SelectMapper.class);
        Map<String, Object> userByIdToMap =mapper.getAllUserToMap();
        System.out.println(userByIdToMap);
    }
}

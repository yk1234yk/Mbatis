package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ParameterMapper {
    /*
    查询所有员工的信息
     */
//    @Select("select * from t_user")
    List<User> getAllUser();

    /*
    根据用户名查询用户信息
     */
    User getUserByUsername(String username);

    /*
    验证登录
     */
    User checkLogin(String username, String password);

    /*
    验证登录（参数为map）
     */
    User checkLoginMap(Map<String, Object> map);

    /*
    添加用户
     */
    int insertUser(User user);

    /*
        验证登录（使用@Param)
     */
//    @Select("select * from t_user where username = #{username} and password = #{password}")
    User checkLoginByParam(@Param("username") String username, @Param("password") String password);

    int insertsUser(User user);

    /*
    修改数据
     */
//    int updateUser(User user);
}

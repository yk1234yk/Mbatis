package com.atguigu.mybatis;

import com.atguigu.mybatis.mapper.DynamicSQLMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DynamicSQLMapperTest {

    /*
    动态SQL
        1.if：根据标签中test属性所对应的表达式决定标签中的内容是否需要拼接到SQL中
        2.where标签中有内容时，会自动生成where关键字，并且将内容前多余的and或or去掉
            当where标签中没有内容时此时where标签没有任何效果
            where标签不能将其中内容多余后面的and或or去掉
        3.trim:
            若标签中有内容时：
            prefix|suffix:将trim标签中内容前面或后面添加指定的内容
            suffixOverrides|prefixOverrides:将trim标签中内容前面或后面删除指定的内容
            若标签中没有内容时：
                trim标签也没有任何效果
         4.choose.when.otherwise,相当于if...else if ...else
            when至少要有一个，otherwise最多只能有一个
         5.foreach
            item:表示数组或集合中的每一个数组
            separator:循环体之间的分隔符
            open:foreach标签所循环的所有内容的开始符
            close:foreach标签所循环的所有内容的结束符
         6.sql标签
            设置sql片段:<sql id="empColumns">eid,emp_name,age,sex,email</sql>
            引用sql片段:select <include refid="empColumns"/> from t_emp

     */
    @Test
    public void testGetEmpByCondition() {
        SqlSession session = SqlSessionUtils.getSession();
        DynamicSQLMapper mapper = session.getMapper(DynamicSQLMapper.class);
        List<Emp> list = mapper.getEmpByCondition(new Emp(null, "", 23, "", ""));
        System.out.println(list);
    }

    @Test
    public void testGetByChoose() {
        SqlSession session = SqlSessionUtils.getSession();
        DynamicSQLMapper mapper = session.getMapper(DynamicSQLMapper.class);
        List<Emp> empByChoose = mapper.getEmpByChoose(new Emp(null, "张三", 23, "男", "123@qq.com"));
        System.out.println(empByChoose);
    }

    @Test
    public void testDeleteMoreByArray() {
        SqlSession session = SqlSessionUtils.getSession();
        DynamicSQLMapper mapper = session.getMapper(DynamicSQLMapper.class);
        int i = mapper.deleteMoreByArray(new Integer[]{6, 7, 8});
        System.out.println(i);

    }

    @Test
    public void testInsertMoreByArray() {
        SqlSession session = SqlSessionUtils.getSession();
        DynamicSQLMapper mapper = session.getMapper(DynamicSQLMapper.class);
        Emp emp1 = new Emp(null, "a1", 21, "男", "456@qq.com");
        Emp emp2 = new Emp(null, "a2", 22, "男", "456@qq.com");
        Emp emp3 = new Emp(null, "a3", 23, "男", "456@qq.com");
        Emp emp4 = new Emp(null, "a4", 24, "男", "456@qq.com");
        List<Emp> emps = Arrays.asList(emp1, emp2, emp3, emp4);
        int i = mapper.insertMoreByList(emps);
        System.out.println(i);
    }
}

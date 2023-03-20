package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.pojo.EmpExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MBGTest {
    @Test
    public void MBGtest() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = build.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            //查询所有数据
            List<Emp> list =mapper.selectByExample(null);
            list.forEach(System.out::println);

            //根据条件查询
//            EmpExample empExample = new EmpExample();
//            empExample.createCriteria().andEidEqualTo(5).andAgeGreaterThanOrEqualTo(10);
//            List<Emp> emps = mapper.selectByExample(empExample);
//            emps.forEach(System.out::println);

//            mapper.updateByPrimaryKeySelective(new Emp(1,"张三",22,null,"456@qq.com",3));

//            DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
//            deptMapper.insertSelective(new Dept(4,"A"));
//            deptMapper.deleteByPrimaryKey(4);
//            deptMapper.insertSelective(new Dept(4,"D"));
//            deptMapper.deleteByExample(new DeptExample(2,"A"));
//            deptMapper.deleteByPrimaryKey(3);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testUpdate(){
        try {
            InputStream resource = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
            SqlSession sqlSession = build.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
//            mapper.updateByPrimaryKeySelective(new Emp(1,"甘雨",23,null,"456@qq.com1",4));
            EmpExample empExample = new EmpExample();
            empExample.createCriteria().andEidEqualTo(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

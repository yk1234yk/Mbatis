package com.atguigu.mybatis;

import com.atguigu.mybatis.mapper.CacheMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class CacheMapperTest {
    @Test
    public void testGetEmpByEid() {
        SqlSession session = SqlSessionUtils.getSession();
        CacheMapper mapper = session.getMapper(CacheMapper.class);
        Emp empByEid = mapper.getEmpByEid(1);
        System.out.println(empByEid);

//        CacheMapper mapper1 = session.getMapper(CacheMapper.class);
//        mapper.insertEmp(new Emp(null,"asd",45,"男","789@qq.com"));
//        session.clearCache();
        Emp empByEid1 = mapper.getEmpByEid(1);
        System.out.println(empByEid1);

        System.out.println(empByEid == empByEid1);
    }

    @Test
    public void testTwoCache() {
        InputStream rs ;
        try {
            rs = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(rs);
            SqlSession sqlSession1 = build.openSession(true);
            SqlSession sqlSession2 = build.openSession(true);

            CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
            System.out.println(mapper1.getEmpByEid(1));
            sqlSession1.close();

            CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);
            System.out.println(mapper2.getEmpByEid(1));
            sqlSession2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

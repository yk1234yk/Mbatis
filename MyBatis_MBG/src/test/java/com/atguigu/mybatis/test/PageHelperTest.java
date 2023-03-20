package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PageHelperTest {


    /*
    limit index pageSize
    index:当前页的起始索引
    pageSize:每页显示的条数
    pageNum:当前页的页码
    index=（pageNum-1）* pageSize

    size：当前页显示的真实条数
    total：总记录数
    pages：总页数
    prePage：上一页的页码
    nextPage：下一页的页码
    isFirstPage/isLastPage：是否为第一页/最后一页
    hasPreviousPage/hasNextPage：是否存在上一页/下一页
    navigatePages：导航分页的页码数
    navigatepageNums：导航分页的页码，[1,2,3,4,5]



    使用MyBatis的分页插件实现分页功能:
        1.需要在查询功能之前开启分页
            PageHelper.startPage(int pageNum,int pageSize)
        2.在查询功能之后获取分页相关信息
            PageInfo<Emp> page = new PageInfo<>(emps, 5);
            emps:表示分页数据
            5:表示当前导航分页的数量
     */
    @Test
    public void testPageHelper() {
        try {
            InputStream rs = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(rs);
            SqlSession sqlSession = build.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
//            Page<Object> page = PageHelper.startPage(2, 4);
            List<Emp> emps = mapper.selectByExample(null);
            PageInfo<Emp> page = new PageInfo<>(emps, 5);
//            emps.forEach(System.out::println);
            System.out.println(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

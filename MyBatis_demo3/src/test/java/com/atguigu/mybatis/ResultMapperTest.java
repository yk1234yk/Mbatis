package com.atguigu.mybatis;

import com.atguigu.mybatis.mapper.DeptMapper;
import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Dept;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class ResultMapperTest {
    /*
    解决字段名和属性名不一致的情况
        1.为字段起别名，保持属性名的一致
        2.设置全局配置，将_自动映射为驼峰
            <setting name="mapUnderscoreToCamelCase" value="true"/>
        3.通过resultMap设置自定义的映射关系
            <resultMap id="empResultMap" type="Emp">
                <id property="eid" column="eid"/>
                <result property="empName" column="emp_name"/>
                <result property="age" column="age"/>
                <result property="sex" column="sex"/>
                <result property="email" column="email"/>
            </resultMap>
            <select id="getAllEmp" resultMap="empResultMap">
                select * from t_emp
            </select>

        处理多对一的映射关系:
            1.级联属性赋值
            2.association
            3.分步查询

        处理一对多的映射关系
            1.collection
            2.分步查询
     */
    @Test
    public void testGetAllEmp() {
        SqlSession session = SqlSessionUtils.getSession();
        EmpMapper mapper = session.getMapper(EmpMapper.class);
        List<Emp> allEmp = mapper.getAllEmp();
        allEmp.forEach(System.out::println);
    }

    @Test
    public void testGetEmpAndDept() {
        SqlSession session = SqlSessionUtils.getSession();
        EmpMapper mapper = session.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDept(2);
        System.out.println(emp);
    }

    @Test
    public void testGetEmpAndDeptByStep() {
        SqlSession session = SqlSessionUtils.getSession();
        EmpMapper mapper = session.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDeptByStepOne(3);
        System.out.println(emp.getEmpName());
    }

    @Test
    public void testGetDeptAndEmp(){
        SqlSession session = SqlSessionUtils.getSession();
        DeptMapper mapper = session.getMapper(DeptMapper.class);
        Dept deptAndEmp = mapper.getDeptAndEmp(1);
        System.out.println(deptAndEmp);
    }


    @Test
    public void testGetDeptAndEmpByStep() {
        SqlSession session = SqlSessionUtils.getSession();
        DeptMapper mapper = session.getMapper(DeptMapper.class);
        Dept deptAndEmp = mapper.getDeptAndEmpByStepOne(1);
        System.out.println(deptAndEmp);
    }
}

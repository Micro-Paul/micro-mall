package org.micro.mappers;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.micro.pojo.YxUserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 只需定义好实体类，然后创建一个接口，继承mp提供的BaseMapper，即可食用。mp会在mybatis启动时，自动解析实体类和表的映射关系，并注入带有通用CRUD方法的mapper。BaseMapper里提供的方法，部分列举如下：
 *
 * @author micro-paul
 * @date 2021年12月22日 10:47
 */
@SpringBootTest
public class YxUserMapperTest {

    @Autowired
    private YxUserMapper yxUserMapper;

    @Test
    public void testSelect() {
        List<YxUserDO> yxUserDOList = yxUserMapper.selectList(null);
        Assertions.assertEquals(1, yxUserDOList.size());
        yxUserDOList.forEach(System.out::println);
    }

    @Test
    public void testSelectMaps() {
        QueryWrapper<YxUserDO> wrapper = new QueryWrapper<>();
        wrapper.select("uid", "username", "real_name").likeRight("username", "222");
        List<Map<String, Object>> mapList = yxUserMapper.selectMaps(wrapper);
        mapList.forEach(System.out::println);

        wrapper.select("uid", "avg(now_money) avg_money", "min(now_money) min_money", "max(now_money) max_money")
                .groupBy("uid").having("sum(now_money) < {0}", 0);
        List<Map<String, Object>> maps = yxUserMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void testSelectObjs() {
        QueryWrapper<YxUserDO> wrapper = new QueryWrapper<>();
        wrapper.select("uid", "username").likeRight("username", "222");
        List<Map<String, Object>> mapList = yxUserMapper.selectMaps(wrapper);
        mapList.forEach(System.out::println);
    }

    @Test
    public void testSelectCount() {
        QueryWrapper<YxUserDO> wrapper = new QueryWrapper<>();
        wrapper.like("username", "222");
        Long aLong = yxUserMapper.selectCount(wrapper);
        System.out.println(aLong);
    }

    /**
     * 实体对象作为条件
     * 调用构造函数创建一个Wrapper对象时，可以传入一个实体对象。后续使用这个Wrapper时，会以实体对象中的非空属性，构建WHERE条件（默认构建等值匹配的WHERE条件，这个行为可以通过实体类里各个字段上的@TableField注解中的condition属性进行改变）
     *
     * @author micro-paul
     * @date 2021/12/22 14:26
     */
    @Test
    public void testObject() {
        YxUserDO user = new YxUserDO();
        user.setUsername("222");
        QueryWrapper<YxUserDO> wrapper = new QueryWrapper<>(user);
        List<YxUserDO> users = yxUserMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testAllEq() {

        QueryWrapper<YxUserDO> wrapper = new QueryWrapper<>();
        Map<String, Object> param = new HashMap<>();
        param.put("uid", 1);
        param.put("username", "222");
        wrapper.allEq(param);
        List<YxUserDO> users = yxUserMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectRaw() {

        List<YxUserDO> yxUserDOList = yxUserMapper.selectRaw();
        Assertions.assertEquals(1, yxUserDOList.size());
        yxUserDOList.forEach(System.out::println);

    }

    @Test
    public void testFindAll() {
        QueryWrapper<YxUserDO> wrapper = new QueryWrapper<>();
        Map<String, Object> param = new HashMap<>();
        param.put("uid", 1);
        param.put("username", "222");
        wrapper.allEq(param);
        List<YxUserDO> yxUserDOList = yxUserMapper.findAll(wrapper);
        Assertions.assertEquals(1, yxUserDOList.size());
        yxUserDOList.forEach(System.out::println);

    }

    @Test
    public void testPage() {
        LambdaQueryWrapper<YxUserDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(YxUserDO::getUsername, "222");
        // 设置分页信息, 查第3页, 每页2条数据
        // 注意到，分页查询总共发出了2次SQL，一次查总记录数，一次查具体数据。若希望不查总记录数，仅查分页结果。可以通过Page的重载构造函数，指定isSearchCount为false即可
        Page<YxUserDO> page = new Page<>(0, 10, false);
        // 执行分页查询
        Page<YxUserDO> userPage = yxUserMapper.selectPage(page, wrapper);
        System.out.println("总记录数 = " + userPage.getTotal());
        System.out.println("总页数 = " + userPage.getPages());
        System.out.println("当前页码 = " + userPage.getCurrent());
        // 获取分页查询结果
        List<YxUserDO> records = userPage.getRecords();
        records.forEach(System.out::println);
    }


}

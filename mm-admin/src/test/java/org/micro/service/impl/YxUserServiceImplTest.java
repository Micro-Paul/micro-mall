package org.micro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.jupiter.api.Test;
import org.micro.pojo.YxUserDO;
import org.micro.service.YxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


/** 另外一套CRUD是Service层的，只需要编写一个接口，继承IService，并创建一个接口实现类，即可食用。（这个接口提供的CRUD方法，和Mapper接口提供的功能大同小异，比较明显的区别在于IService支持了更多的批量化操作，如saveBatch，saveOrUpdateBatch等方法。
 * @author micro-paul
 * @date 2021年12月22日 10:47
 */
@SpringBootTest
public class YxUserServiceImplTest {

    @Autowired
    private YxUserService yxUserService;

    @Test
    public void testGetOne() {
        LambdaQueryWrapper<YxUserDO> wrapper = Wrappers.<YxUserDO>lambdaQuery();
        wrapper.eq(YxUserDO::getUid, 1);
        // 第二参数指定为false,使得在查到了多行记录时,不抛出异常,而返回第一条记录
        YxUserDO one = yxUserService.getOne(wrapper, false);
        System.out.println(one);
    }

    @Test
    public void testChain() {
        List<YxUserDO> list = yxUserService.lambdaQuery()
                .eq(YxUserDO::getUid, 1)
                .likeRight(YxUserDO::getUsername, "222")
                .list();
        list.forEach(System.out::println);
    }

}
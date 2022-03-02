package org.micro.mappers;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.micro.pojo.YxUserDO;

import java.util.List;

/**
 * @author micro-paul
 * @date 2021年12月22日 10:31
 */
public interface YxUserMapper extends BaseMapper<YxUserDO> {

    /**
     *
     * @author micro-paul
     * @date 2021/12/22 15:09
     * @return java.util.List<org.micro.pojo.YxUserDO>
     */
    @Select("select * from yx_user")
    List<YxUserDO> selectRaw();

    /**
     * SQL中不写WHERE关键字，且固定使用${ew.customSqlSegment}
     * @author micro-paul
     * @date 2021/12/22 15:12
     * @param wrapper
     * @return java.util.List<org.micro.pojo.YxUserDO>
     */
    @Select("select * from yx_user ${ew.customSqlSegment}")
    List<YxUserDO> findAll(@Param(Constants.WRAPPER) Wrapper<YxUserDO> wrapper);

    /**
     * 在实际开发中，可能遇到多表联查的场景，此时BaseMapper中提供的单表分页查询的方法无法满足需求，需要自定义SQL，示例如下（使用单表查询的SQL进行演示，实际进行多表联查时，修改SQL语句即可）
     * @author micro-paul
     * @date 2021/12/22 15:26
     * @param page
     * @param wrapper
     * @return Page<User>
     */
    @Select("SELECT * FROM yx_user ${ew.customSqlSegment}")
    Page<YxUserDO> selectUserPage(Page<YxUserDO> page, @Param(Constants.WRAPPER) Wrapper<YxUserDO> wrapper);

}

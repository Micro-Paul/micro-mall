package org.micro.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author micro-paul
 * @date 2021年12月22日 15:20
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 配置乐观锁
        // interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        // 添加多租户拦截器配置。添加配置后，在执行CRUD的时候，会自动在SQL语句最后拼接租户id的条件
        // interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
        //     @Override
        //     public Expression getTenantId() {
        //         // 返回租户id的值, 这里固定写死为1
        //         // 一般是从当前上下文中取出一个 租户id
        //         return new LongValue(1);
        //     }
        //
        //     /**
        //      ** 通常会将表示租户id的列名，需要排除租户id的表等信息，封装到一个配置类中（如TenantConfig）
        //      **/
        //     @Override
        //     public String getTenantIdColumn() {
        //         // 返回表中的表示租户id的列名
        //         return "manager_id";
        //     }
        //
        //     @Override
        //     public boolean ignoreTable(String tableName) {
        //         // 表名不为 user2 的表, 不拼接多租户条件
        //         return !"user2".equals(tableName);
        //     }
        // }));

        // 如果用了分页插件注意先 add TenantLineInnerInterceptor 再 add PaginationInnerInterceptor
        // 用了分页插件必须设置 MybatisConfiguration#useDeprecatedExecutor = false

        // 当数据量特别大的时候，我们通常会采用分库分表。这时，可能就会有多张表，其表结构相同，但表名不同。
        // 例如order_1，order_2，order_3，查询时，我们可能需要动态设置要查的表名。mp提供了动态表名SQL解析器，食用示例如下
        // DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        // HashMap<String, TableNameHandler> map = new HashMap<>();
        // 对于user2表，进行动态表名设置
        // dynamicTableNameInnerInterceptor.setTableNameHandler(new TableNameHandler() {
        //     @Override
        //     public String dynamicTableName(String sql, String tableName) {
        //         String _ = "_";
        //         int random = new Random().nextInt(2) + 1;
        //         // 若返回null, 则不会进行动态表名替换, 还是会使用user2
        //         return tableName + _ + random;
        //     }
        // });
        // interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);
        return interceptor;
    }
}

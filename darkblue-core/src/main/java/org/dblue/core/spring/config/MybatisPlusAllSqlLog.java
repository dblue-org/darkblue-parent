package org.dblue.core.spring.config;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

public class MybatisPlusAllSqlLog implements InnerInterceptor {

    // 记录 SQL 日志的日志器
    public static final Logger log = LoggerFactory.getLogger("sys-sql");

    /**
     * 日志打印的核心逻辑
     *
     * @param boundSql  BoundSql实例
     * @param ms        MappedStatement实例
     * @param parameter 参数对象
     */
    private static void logInfo(BoundSql boundSql, MappedStatement ms, Object parameter) {
        try {
            log.info("parameter = {}", parameter);  // 使用占位符方式更为安全且性能优越
            String sqlId = ms.getId();  // 获取SQL的ID
            log.info("sqlId = {}", sqlId);
            Configuration configuration = ms.getConfiguration();  // 获取配置对象
            String sql = getSql(configuration, boundSql, sqlId);  // 获取最终的SQL语句
            log.info("完整的sql: {}", sql);
        } catch (Exception e) {
            log.error("异常: {}", e.getLocalizedMessage(), e);  // 捕获异常并打印
        }
    }

    /**
     * 封装SQL语句信息，返回SQL节点id和对应的SQL语句
     *
     * @param configuration 配置对象
     * @param boundSql      BoundSql实例
     * @param sqlId         SQL节点ID
     * @return 完整的SQL信息
     */
    public static String getSql(Configuration configuration, BoundSql boundSql, String sqlId) {
        return sqlId + ":" + showSql(configuration, boundSql);  // 返回完整的sql信息
    }

    /**
     * 替换SQL语句中的占位符？为实际的参数值
     *
     * @param configuration 配置对象
     * @param boundSql      BoundSql实例
     * @return 替换后的SQL语句
     */
    public static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();  // 获取参数对象
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();  // 获取SQL中所有的参数映射
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");  // 清除SQL中的多余空格

        // 如果存在参数且参数对象不为空，则替换占位符
        if (!CollectionUtils.isEmpty(parameterMappings) && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();  // 获取类型处理器注册器
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);  // 获取MetaObject，用于获取参数属性
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        // 动态SQL中的额外参数
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    } else {
                        // 如果缺少参数，打印“缺失”
                        sql = sql.replaceFirst("\\?", "缺失");
                    }
                }
            }
        }
        return sql;
    }

    /**
     * 将参数对象转化为对应的SQL表示形式（例如：字符串加引号，日期加引号等）
     *
     * @param obj 参数对象
     * @return 转换后的参数值
     */
    private static String getParameterValue(Object obj) {
        if (obj == null) {
            return "NULL";  // 处理null情况
        }

        if (obj instanceof String) {
            return "'" + obj + "'";  // 字符串加单引号
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            return "'" + formatter.format(obj) + "'";  // 日期格式化后加单引号
        } else {
            return obj.toString();  // 其他类型直接调用toString()
        }
    }

    /**
     * 查询前的拦截
     *
     * @param executor      Executor实例
     * @param ms            MappedStatement实例
     * @param parameter     查询参数
     * @param rowBounds     分页信息
     * @param resultHandler 结果处理器
     * @param boundSql      BoundSql实例
     * @throws SQLException SQL异常
     */
    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        logInfo(boundSql, ms, parameter);
    }

    /**
     * 更新前的拦截
     *
     * @param executor  Executor实例
     * @param ms        MappedStatement实例
     * @param parameter 更新参数
     * @throws SQLException SQL异常
     */
    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        BoundSql boundSql = ms.getBoundSql(parameter);
        logInfo(boundSql, ms, parameter);
    }
}

package org.guohai.javasqlweb.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.guohai.javasqlweb.beans.ConnectConfigBean;
import org.guohai.javasqlweb.beans.QueryLogBean;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 基础信息DAO
 * @author guohai
 */
@Repository
public interface BaseConfigDao {

    /**
     * 返回完整的配置项
     * @return
     */
    @Select("SELECT code,db_server_name,db_server_type FROM db_connect_config_tb;")
    List<ConnectConfigBean> getAllConnectConfig();

    /**
     * 获得指定code的连接属性
     * @param code
     * @return
     */
    @Select("SELECT * FROM db_connect_config_tb WHERE code=#{code}")
    ConnectConfigBean getConnectConfig(@Param("code") Integer code);

    /**
     * 保存查询日志
     * @param user
     * @param dbname
     * @param sql
     * @param userIp
     * @return
     */
    @Insert("INSERT INTO `db_query_log`\n" +
            "(`query_ip`,\n" +
            "`query_name`,\n" +
            "`query_database`,\n" +
            "`query_sqlscript`,\n" +
            "`query_time`)\n" +
            "VALUES\n" +
            "(#{ip},\n" +
            "#{user},\n" +
            "#{dbname},\n" +
            "#{sql},\n" +
            "#{log_date});")
    Boolean saveQueryLog(@Param("user") String user, @Param("dbname") String dbname,
                         @Param("sql") String sql, @Param("ip") String userIp, @Param("log_date")Date logDate);

    /**
     * 倒序查询日志
     * @return
     */
    @Select("SELECT * FROM db_query_log ORDER BY code DESC;")
    List<QueryLogBean> getQueryLog();

    /**
     * 获取所有的连接配置
     * @return
     */
    @Select("SELECT `code`,`db_server_name`,`db_server_host`,`db_server_port`,`db_server_username`,\n" +
            "'*' as `db_server_password`,`db_server_type`,`create_time`\n" +
            "FROM `db_connect_config_tb`;")
    List<ConnectConfigBean> getConnData();
}

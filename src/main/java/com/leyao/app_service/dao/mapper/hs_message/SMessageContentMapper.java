package com.leyao.app_service.dao.mapper.hs_message;

import com.leyao.app_service.entity.hs_message.SMessageContent;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface SMessageContentMapper {
    @Delete({
        "delete from s_message_content",
        "where s_message_content_id = #{sMessageContentId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long sMessageContentId);

    @Insert({
        "insert into s_message_content (s_message_content_id, h_message_id, ",
        "s_message_content_str, create_ts, ",
        "update_ts)",
        "values (#{sMessageContentId,jdbcType=BIGINT}, #{hMessageId,jdbcType=BIGINT}, ",
        "#{sMessageContentStr,jdbcType=VARCHAR}, #{createTs,jdbcType=TIMESTAMP}, ",
        "#{updateTs,jdbcType=TIMESTAMP})"
    })
    int insert(SMessageContent record);

    @InsertProvider(type=SMessageContentSqlProvider.class, method="insertSelective")
    int insertSelective(SMessageContent record);

    @Select({
        "select",
        "s_message_content_id, h_message_id, s_message_content_str, create_ts, update_ts",
        "from s_message_content",
        "where s_message_content_id = #{sMessageContentId,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="s_message_content_id", property="sMessageContentId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="h_message_id", property="hMessageId", jdbcType=JdbcType.BIGINT),
        @Result(column="s_message_content_str", property="sMessageContentStr", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_ts", property="createTs", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_ts", property="updateTs", jdbcType=JdbcType.TIMESTAMP)
    })
    SMessageContent selectByPrimaryKey(Long sMessageContentId);

    @UpdateProvider(type=SMessageContentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SMessageContent record);

    @Update({
        "update s_message_content",
        "set h_message_id = #{hMessageId,jdbcType=BIGINT},",
          "s_message_content_str = #{sMessageContentStr,jdbcType=VARCHAR},",
          "create_ts = #{createTs,jdbcType=TIMESTAMP},",
          "update_ts = #{updateTs,jdbcType=TIMESTAMP}",
        "where s_message_content_id = #{sMessageContentId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SMessageContent record);
}
package com.boot.multi.datasource.mybatis.dao.server;

import com.boot.multi.datasource.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * @author Xingyu Sun
 */
@Mapper
public interface RoleDao extends ServerDao{

    /**
     * findAll
     * @return all users
     */
    @Select("select * from user")
    List<User> findAll();
}

package com.zyk.mapper;

import com.zyk.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into user(username,password,name,email,addr) " +
            "value(#{username},#{password},#{name},#{email},#{addr})")
    void addUser(@Param("username") String username,@Param("password") String password,@Param("name") String name,
                 @Param("email") String email, @Param("addr") String addr);
    @Select("select username,password from user where username=#{username} and password=#{password}")
    List<User> loginCheck(@Param("username") String username, @Param("password") String password);

}

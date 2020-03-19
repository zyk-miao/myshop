package com.zyk.mapper;

import com.zyk.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AdminMapper {
    @Select("select * from admin where adminname=#{adminname} and adminpassword=#{adminpassword}")
    List<Admin> adminLogin(@Param("adminname") String adminname,@Param("adminpassword") String adminpassword);
    @Update("update admin set adminpassword=#{password} where adminname=#{adminname}")
    void updatePassword(@Param("adminname") String adminname,@Param("password") String password);
}

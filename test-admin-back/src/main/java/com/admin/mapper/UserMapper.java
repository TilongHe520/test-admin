package com.admin.mapper;

import com.admin.pojo.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    UserInfo findByUsername(String username);

    List<UserInfo> findAll();
}

package com.admin.mapper;

import com.admin.pojo.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    UserInfo findByUsername(String username);
}

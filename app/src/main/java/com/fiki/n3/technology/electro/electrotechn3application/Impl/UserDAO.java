package com.fiki.n3.technology.electro.electrotechn3application.Impl;


import com.fiki.n3.technology.electro.electrotechn3application.dto.UserDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/16.
 */
public interface UserDAO {
    public Long create(UserDTO userDTO);
    public int update(UserDTO userDTO);
    public int delete(Integer userId);
    public int deleteTable();
    public UserDTO findByIdUserId(Integer userId);
    public UserDTO findByLoginId(Integer loginId);
    public HashMap getList();
}

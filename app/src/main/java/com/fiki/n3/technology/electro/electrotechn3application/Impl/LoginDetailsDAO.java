package com.fiki.n3.technology.electro.electrotechn3application.Impl;

import com.fiki.math.lab.mathlab.domain.model.LoginDetails;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/15.
 */
public interface LoginDetailsDAO {

    public Long create(LoginDetails loginDetails);
    public int update(LoginDetails loginDetails);
    public int delete(LoginDetails loginDetails);
    public int deleteTable();
    public LoginDetails findByUserName(String username);
    public HashMap getList();
}

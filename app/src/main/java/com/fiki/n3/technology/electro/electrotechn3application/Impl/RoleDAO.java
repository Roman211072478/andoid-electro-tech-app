package com.fiki.n3.technology.electro.electrotechn3application.Impl;

import com.fiki.math.lab.mathlab.domain.model.Role;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/16.
 */
public interface RoleDAO {
    public Long create(Role role);
    public int update(Role role);
    public int delete(Integer roleId);
    public int deleteTable();
    public Role findById(Integer roleId);
    public HashMap getList();
}

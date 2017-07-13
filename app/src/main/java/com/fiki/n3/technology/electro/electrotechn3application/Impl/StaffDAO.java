package com.fiki.n3.technology.electro.electrotechn3application.Impl;


import com.fiki.n3.technology.electro.electrotechn3application.dto.StaffDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/16.
 */
public interface StaffDAO {
    public Long create(StaffDTO staffDTO);
    public int update(StaffDTO staffDTO);
    public int delete(Integer staffId);
    public int deleteTable();
    public StaffDTO findByIdStaffId(Integer staffId);
    public StaffDTO findByLoginId(Integer loginId);
    public HashMap getList();
}

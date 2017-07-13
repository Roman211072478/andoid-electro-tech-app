package com.fiki.n3.technology.electro.electrotechn3application.Impl;


import com.fiki.n3.technology.electro.electrotechn3application.dto.TableVersionDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/06/15.
 */
public interface TableVersionDAO {
    public Long create(TableVersionDTO dto);
    public int update(TableVersionDTO dto);
    public int delete(Integer id);
    public int deleteTable();
    public TableVersionDTO findById(String type);
    public HashMap getList();
}

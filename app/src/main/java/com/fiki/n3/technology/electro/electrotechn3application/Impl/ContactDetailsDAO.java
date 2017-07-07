package com.fiki.n3.technology.electro.electrotechn3application.Impl;

import com.fiki.math.lab.mathlab.domain.model.ContactDetails;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/14.
 */
public interface ContactDetailsDAO {
    public Long create(ContactDetails backEmf, Integer staff_id);
    public int update(ContactDetails backEmf, Integer staff_id);
    public int delete(Integer staff_id);
    public int deleteTable();
    public ContactDetails findById(Integer staff_id);
    public HashMap getList();

}

package com.fiki.n3.technology.electro.electrotechn3application.Impl;

import com.fiki.math.lab.mathlab.domain.model.BackEmf;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/14.
 */
public interface BackEmfDAO {

    public Long create(BackEmf backEmf, Integer tutorialId);
    public int update(BackEmf backEmf, Integer tutorialId);
    public int delete(Integer tutorialId);
    public int deleteTable();
    public BackEmf findById(Integer tutorialId);
    public HashMap getList();
    

}

package com.fiki.n3.technology.electro.electrotechn3application.Impl;

import com.fiki.math.lab.mathlab.domain.model.RopeBreakMethod;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/16.
 */
public interface RopeBreakDAO {
    public Long create(RopeBreakMethod ropeBreakMethod, Integer tutorialId);
    public int update(RopeBreakMethod ropeBreakMethod, Integer tutorialId);
    public int delete(Integer tutorialId);
    public int deleteTable();
    public RopeBreakMethod findByIdTutorialId(Integer tutorialId);
    public HashMap getList();
}

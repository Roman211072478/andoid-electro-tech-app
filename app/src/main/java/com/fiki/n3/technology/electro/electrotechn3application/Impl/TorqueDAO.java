package com.fiki.n3.technology.electro.electrotechn3application.Impl;

import com.fiki.math.lab.mathlab.domain.model.Torque;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/16.
 */
public interface TorqueDAO {
    public Long create(Torque pronyBreakEfficiency, Integer tutorialId);
    public int update(Torque pronyBreakEfficiency, Integer tutorialId);
    public int delete(Integer tutorialId);
    public int deleteTable();
    public Torque findByIdTutorialId(Integer tutorialId);
    public HashMap getList();
}

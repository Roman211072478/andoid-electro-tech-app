package com.fiki.n3.technology.electro.electrotechn3application.Impl;

import com.fiki.math.lab.mathlab.domain.model.PronyBreakEfficiency;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/16.
 */
public interface PronyBreakDAO {
    public Long create(PronyBreakEfficiency pronyBreakEfficiency, Integer tutorialId);
    public int update(PronyBreakEfficiency pronyBreakEfficiency, Integer tutorialId);
    public int delete(Integer tutorialId);
    public int deleteTable();
    public PronyBreakEfficiency findByIdTutorialId(Integer tutorialId);
    public HashMap getList();
}

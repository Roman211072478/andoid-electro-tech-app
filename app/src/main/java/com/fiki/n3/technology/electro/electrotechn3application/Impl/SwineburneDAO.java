package com.fiki.n3.technology.electro.electrotechn3application.Impl;

import com.fiki.math.lab.mathlab.domain.model.SwinburneMethod;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/16.
 */
public interface SwineburneDAO {
    public Long create(SwinburneMethod swinburneMethod, Integer tutorialId);
    public int update(SwinburneMethod swinburneMethod, Integer tutorialId);
    public int delete(Integer tutorialId);
    public int deleteTable();
    public SwinburneMethod findByIdTutorialId(Integer tutorialId);
    public HashMap getList();
}

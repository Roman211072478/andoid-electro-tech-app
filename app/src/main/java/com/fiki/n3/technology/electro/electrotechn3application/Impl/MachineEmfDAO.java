package com.fiki.n3.technology.electro.electrotechn3application.Impl;

import com.fiki.math.lab.mathlab.domain.model.MachineEmf;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/16.
 */
public interface MachineEmfDAO {
    public Long create(MachineEmf machineEmf, Integer tutorialId);
    public int update(MachineEmf machineEmf, Integer tutorialId);
    public int delete(Integer tutorialId);
    public int deleteTable();
    public MachineEmf findByIdTutorialId(Integer tutorialId);
    public HashMap getList();
}

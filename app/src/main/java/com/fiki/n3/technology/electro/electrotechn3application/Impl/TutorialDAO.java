package com.fiki.n3.technology.electro.electrotechn3application.Impl;


import com.fiki.n3.technology.electro.electrotechn3application.dto.TutorialDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/16.
 */
public interface TutorialDAO {
    public Long create(TutorialDTO tutorial);
    public int update(TutorialDTO tutorial);
    public int delete(Integer tutorialId);
    public int deleteTable();
    public TutorialDTO findByIdTutorialId(Integer tutorialId);
    public HashMap getList();
}

package com.fiki.n3.technology.electro.electrotechn3application.Impl;

import com.fiki.math.lab.mathlab.domain.model.Equation;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/14.
 */
public interface EquationDAO {
    public Long create(Equation equation);
    public int update(Equation equation);
    public int delete(Integer equationId);
    public int deleteTable();
    public Equation findById(Integer equationId);
    public HashMap getList();
}

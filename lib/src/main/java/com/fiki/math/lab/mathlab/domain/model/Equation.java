/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.domain.model;

import com.fiki.math.lab.mathlab.factories.EquationFactoryChain;

import java.io.Serializable;

/**
 *
 * @author Roman
 */
public class Equation implements Serializable{

    private String formula;
    private String output;
    private String type;

    public Integer getEquationId() {
        return equationId;
    }

    public void setEquationId(Integer equationId) {
        this.equationId = equationId;
    }

    private Integer equationId;

    public Equation()
    {}
    public void setFormula(String type,String equationOutput)
    {
        this.type = type;
        this.output = equationOutput;
         this.formula = EquationFactoryChain.getEquation(type,equationOutput);
    }
    public String getFormula()
    {
        return formula;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

}

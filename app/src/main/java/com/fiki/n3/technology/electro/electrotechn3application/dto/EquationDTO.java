package com.fiki.n3.technology.electro.electrotechn3application.dto;

import com.fiki.math.lab.mathlab.domain.model.Equation;

import java.io.Serializable;

/**
 * Created by Roman on 2016/04/17.
 */
public class EquationDTO implements Serializable {

    private Equation equation;
	private String equationOutput;
	private String equationType;
    private Integer equationId;



    public EquationDTO(Builder builder)
    {


        this.equation = builder.equation;        
		this.equationOutput = builder.equationOutput;
		this.equationType = builder.equationType;
        this.equationId = builder.equationId;
    }

    public Integer getEquationId() {
        return equationId;
    }

    //builder
    public static class Builder{
        private Equation equation;        
		private String equationOutput;
		private String equationType;

        private Integer equationId;

        public Builder equationId(Integer equationId)
        {
            this.equationId = equationId;
            return this;
        }


       	   
        public Builder equation(Equation equation)
        {
            this.equation = equation;
            return this;
        }
        
		 public Builder equationOutput(String equationOutput)
        {
            this.equationOutput = equationOutput;
            return this;
        }
        public Builder equationType(String equationType)
        {
            this.equationType = equationType;
            return this;
        }

        public Builder copy(EquationDTO value)
        {
            this.equation = value.getEquation();
            this.equationType = value.getEquationType();
            this.equationOutput = value.getEquationOutput();

            this.equationId = value.getEquationId();

            return this;
        }

        public EquationDTO build(){
            return new EquationDTO(this);
        }
    }

    public Equation getEquation()
    {
        return this.equation;
    }

    public String getEquationType()
    {
        return equationType;
    }
	public String getEquationOutput()
    {
        return equationOutput;
    }


}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.domain.model;

import java.io.Serializable;

/**
 *
 * @author Roman
 */
public class SwinburneMethod implements ElectroTechN3,Serializable {
  
    private double noLoadLoss; 
    private double shuntFieldLoss;
    private double fullLoadAmatureLoss;
   
    private double totalLoss; 
    private double output;  
    private double input; 
    private double efficiency; 
    
  public SwinburneMethod(Builder builder)
    {
        //default constructor
          this.efficiency=builder.efficiency;
            this.fullLoadAmatureLoss=builder.fullLoadAmatureLoss;
            this.input=builder.input;
            this.noLoadLoss=builder.noLoadLoss;
            this.output=builder.output;
            this.shuntFieldLoss=builder.shuntFieldLoss;
            this.totalLoss=builder.totalLoss;            
    }

    @Override
    public double result() {
        return efficiency;
    }
  //builder
    public static class Builder{
        
    private double noLoadLoss; 
    private double shuntFieldLoss;
    private double fullLoadAmatureLoss;
    private double totalLoss; 
    private double output;  
    private double input; 
    private double efficiency;
        
        public Builder noLoad(double noLoad)
        {
            this.noLoadLoss = noLoad;
            return this;
        }
        public Builder shuntFieldLoss(double shuntFieldLoss)
        {
            this.shuntFieldLoss = shuntFieldLoss;
            return this;
        }
        public Builder fullLoadArmatureLoss(double fullLoadArmatureLoss)
        {
            this.fullLoadAmatureLoss = fullLoadArmatureLoss;
            return this;
        }
        public Builder output(double output)
        {
            this.output = output;
            return this;
        }
        public Builder input(double input)
        {
            this.input = input;
            return this;
        }
        private Builder totalLoss()
        {
            this.totalLoss = this.noLoadLoss + fullLoadAmatureLoss + shuntFieldLoss;
            return this;
        }
            
        
        public Builder efficiency(double efficiency)
        {
            this.efficiency = efficiency;
            return this;
        }
        
        public Builder copy(SwinburneMethod value)
        {
            this.efficiency=value.efficiency;
            this.fullLoadAmatureLoss=value.fullLoadAmatureLoss;
            this.input=value.input;
            this.noLoadLoss=value.noLoadLoss;
            this.output=value.output;
            this.shuntFieldLoss=value.shuntFieldLoss;
            this.totalLoss=value.totalLoss;
            
            return this;
        }

        public SwinburneMethod build(){
            totalLoss();
            return new SwinburneMethod(this);
        }

    
    }
    
    public double getNoLoadLoss()
    {
        return this.noLoadLoss;
    }
     public double getShuntFieldLoss()
    {
        return this.shuntFieldLoss;
    }
     public double getFullLoadAmatureLoss()
    {
        return this.fullLoadAmatureLoss;
    }
     public double getTotalLoss()
    {
        return this.totalLoss;
    }
     public double getOutput()
    {
        return this.output;
    }
     public double getInput()
    {
        return this.input;
    }
    public double getEfficiency()
    {
        return this.efficiency;
    }
    
}

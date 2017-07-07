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
public class BackEmf implements ElectroTechN3, Serializable{
   
    private double armatureCurrent;
    private double resistance;  
    private double volts; 
    private double backEmf;
   
  public BackEmf(Builder builder)
    {
        //default constructor
           this.backEmf=builder.backEmf;
            this.armatureCurrent=builder.armatureCurrent;
            this.volts=builder.volts;
            this.resistance =builder.resistance;
    }

    @Override
    public double result() {
       return this.backEmf;
    }

  //builder
    public static class Builder{
    
    private double volts; 
    private double armatureCurrent;      
    private double backEmf;
    private double resistance;
        
    
        public Builder resistance(double resistance)
        {
            this.resistance = resistance;
            return this;
        }
        public Builder volts(double volts)
        {
            this.volts = volts;
            return this;
        }
        public Builder armatureCurrent(double armatureCurrent)
        {
            this.armatureCurrent = armatureCurrent;
            return this;
        }
        public Builder backEmf(double backEmf)
        {
            this.backEmf = backEmf;
            return this;
        }
        
        public Builder copy(BackEmf value)
        {
            this.backEmf=value.backEmf;
            this.armatureCurrent=value.armatureCurrent ;
            this.resistance=value.resistance;
            this.volts=value.volts;
            
            return this;
        }

        public BackEmf build(){
            return new BackEmf(this);
        }

    }
    
       public double getResistance()
    {
        return this.resistance;
    }
     public double getArmatureCurrent()
    {
        return this.armatureCurrent;
    }
     public double getVolts()
    {
        return this.volts;
    }
}

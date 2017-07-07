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
public class PronyBreakEfficiency implements ElectroTechN3,Serializable {
     
    private double speed;


    private Integer id;
    private double armsLength; 
    private double current;  
    private double volts; 
    private double weight; 
    private double tension; 
    private double pronyBreakEfficiency;
   
  public PronyBreakEfficiency(Builder builder)
    {
        //default constructor
           this.pronyBreakEfficiency=builder.pronyBreakEfficiency;
            this.current=builder.current;
            this.speed=builder.speed;
            this.tension=builder.tension;
            this.id=builder.id;

            this.volts=builder.volts;
            this.weight=builder.weight;           
            this.armsLength =builder.armsLength;
    }


   
  //builder
    public static class Builder{
        
    private double speed;
    private Integer id;
    private double volts;
    private double current;  
    private double weight; 
    private double tension; 
    private double pronyBreakEfficiency;
    private double armsLength;
        
    
        public Builder armsLength(double armsLength)
        {
            this.armsLength = armsLength;
            return this;
        }
      public Builder id(Integer id)
        {
            this.id = id;
            return this;
        }
        public Builder speed(double speed)
        {
            this.speed = speed;
            return this;
        }
       
        public Builder volts(double volts)
        {
            this.volts = volts;
            return this;
        }
        public Builder current(double current)
        {
            this.current = current;
            return this;
        }
        public Builder weight(double weight)
        {
            this.weight = weight;
            return this;
        }
        
        public Builder tension(double tension)
        {
            this.tension = tension;
            return this;
        }
        public Builder pronyBreakEfficiency(double pronyBreakEfficiency)
        {
            this.pronyBreakEfficiency = pronyBreakEfficiency;
            return this;
        }
        
        public Builder copy(PronyBreakEfficiency value)
        {
            this.pronyBreakEfficiency=value.pronyBreakEfficiency;
            this.current=value.current;
            this.speed=value.speed;
            this.tension=value.tension;
            this.id=value.id;

            this.volts=value.volts;
            this.weight=value.weight;
            this.armsLength = value.armsLength;
           
            return this;
        }

        public PronyBreakEfficiency build(){
            return new PronyBreakEfficiency(this);
        }

    }
    
       public double getArmsLength()
    {
        return this.armsLength;
    }

    /* Getters */
    @Override
    public double result() {
        return this.pronyBreakEfficiency;
    }
    public double getCurrent()
    {
        return this.current;
    }
     public double getSpeed()
    {
        return this.speed;
    }
     public double getTension()
    {
        return this.tension;
    }
   
     public double getVolts()
    {
        return this.volts;
    }
     public double getWeight()
    {
        return this.weight;
    }
    public Integer getId() {
        return id;
    }



}

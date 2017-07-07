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
public class RopeBreakMethod implements ElectroTechN3,Serializable {

    private double speed;
    private double radius;
    private double volts; 
    private double current;  
    private double weight; 
    private double tension; 
    private double ropeBreakEfficiency;
    private Integer id;
   
  public RopeBreakMethod(Builder builder)
    {
        //default constructor
           this.ropeBreakEfficiency=builder.ropeBreakEfficiency;
            this.current=builder.current;
            this.speed=builder.speed;
            this.tension=builder.tension;
            this.volts=builder.volts;
            this.weight=builder.weight;           
            this.radius=builder.radius;
            this.id =builder.id;
    }

    @Override
    public double result() {
       return this.ropeBreakEfficiency;
    }

    public Integer getId() {
        return id;
    }

    //builder
    public static class Builder{
    private double radius;
    private Integer id;
    private double speed;
    private double volts; 
    private double current;  
    private double weight; 
    private double tension; 
    private double ropeBreakEfficiency;


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
        public Builder RopeBreakEfficiency(double RopeBreakEfficiency)
        {
            this.ropeBreakEfficiency = RopeBreakEfficiency;
            return this;
        }
        
        public Builder radius(double radius)
        {
            this.radius = radius;
            return this;
        }
        
        
        public Builder copy(RopeBreakMethod value)
        {
            this.id = value.id;
            this.ropeBreakEfficiency=value.ropeBreakEfficiency;
            this.current=value.current;
            this.speed=value.speed;
            this.tension=value.tension;
          
            this.volts=value.volts;
            this.weight=value.weight;
            this.radius = value.radius;
           
            return this;
        }

        public RopeBreakMethod build(){
            return new RopeBreakMethod(this);
        }

    }
    
    public double getRadius()
    {
        return this.radius;
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

    
}

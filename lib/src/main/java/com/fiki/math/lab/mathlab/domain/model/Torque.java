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
public class Torque implements ElectroTechN3,Serializable {

    private double armatureConductors;
    private double polePairs;


    private Integer id;
   
    private double flux; 
    private double armatureCurrent;  
    private double parallelPaths; 

    private double torque;
   
  public Torque(Builder builder)
    {
        //default constructor
           this.torque=builder.torque;
            this.armatureCurrent=builder.armatureCurrent;
            this.armatureConductors=builder.armatureConductors;

            this.polePairs=builder.polePairs;
            this.parallelPaths=builder.parallelPaths;

            this.flux =builder.flux;
            this.id =builder.id;
    }

    @Override
    public double result() {
       return this.torque;
    }
   
  //builder
    public static class Builder
  {
        private Integer id;
        private double armatureCurrent;
        private double armatureConductors;
        private double polePairs;
        private double parallelPaths;

        private double torque;
        private double flux;

         public Builder id(Integer id)
        {
            this.id = id;
            return this;
        }
      public Builder torque(double torque)
        {
            this.torque = torque;
            return this;
        }
        public Builder flux(double flux)
        {
            this.flux = flux;
            return this;
        }
        public Builder armatureCurrent(double armatureCurrent)
        {
            this.armatureCurrent = armatureCurrent;
            return this;
        }
        public Builder armatureConductors(double armatureConductors)
        {
            this.armatureConductors = armatureConductors;
            return this;
        }
        public Builder polePairs(double polePairs)
        {
            this.polePairs = polePairs;
            return this;
        }
        public Builder parallelPaths(double parallelPaths)
        {
            this.parallelPaths = parallelPaths;
            return this;
        }
       
        
        
        public Builder copy(Torque value)
        {
            this.armatureConductors=value.armatureConductors;
            this.parallelPaths=value.parallelPaths;
            this.armatureCurrent=value.armatureCurrent;            
            this.torque=value.torque;
            this.polePairs=value.polePairs;
            this.flux = value.flux;
            this.id = value.id;

            return this;
        }

        public Torque build(){
            return new Torque(this);
        }

    }
    
       public double getFlux()
    {
        return this.flux;
    }
    public double getArmatureCurrent()
    {
        return this.armatureCurrent;
    }
     public double getArmatureConductors()
    {
        return this.armatureConductors;
    }
     
     public double getPolePairs()
    {
        return this.polePairs;
    }
     public double getParallelPaths()
    {
        return this.parallelPaths;
    }
    public Integer getId() {
        return id;
    }


}

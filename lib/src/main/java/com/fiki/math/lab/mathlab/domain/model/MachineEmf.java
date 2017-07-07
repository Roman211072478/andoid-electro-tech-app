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
public  class MachineEmf implements ElectroTechN3,Serializable {
    
    private double emf; //generated emf
    private double polePairs; // number of poles divide by 2
    private double speedRevolution; // r/m
    private double armatureConductors; 
    private double flux; //measured in webers
    private double parallelPaths; // in text books refered to as c 
    private final String FORMULA = "emf = (2*Conductors*speed*polePairs*flux)  / (parallel paths*60sec)";
    
  
    
    public MachineEmf(Builder builder)
    {
        //default constructor
          this.emf=builder.emf;
            this.polePairs=builder.polePairs;
            this.speedRevolution=builder.speedRevolution;
            this.armatureConductors=builder.armatureConductors;
            this.flux=builder.flux;
            this.parallelPaths=builder.parallelPaths;
    }

    @Override
    public double result() {
        return emf;
    }

   
    
    //builder
    public static class Builder{
        private double emf;
        private double polePairs; 
        private double speedRevolution;
        private double armatureConductors; 
        private double flux; 
        private double parallelPaths;
        
        public Builder emf(double emf)
        {
            this.emf = emf;
            return this;
        }
        public Builder polePairs(double polePairs)
        {
            this.polePairs = polePairs;
            return this;
        }
        public Builder speedRevolution(double speedRevolution)
        {
            this.speedRevolution = speedRevolution;
            return this;
        }
        public Builder armatureConductors(double armatureConductors)
        {
            this.armatureConductors = armatureConductors;
            return this;
        }
        public Builder flux(double flux)
        {
            this.flux = flux;
            return this;
        }
        
        public Builder parallelPaths(double parallelPaths)
        {
            this.parallelPaths = parallelPaths;
            return this;
        }
        
        public Builder copy(MachineEmf value)
        {
            this.emf=value.emf;
            this.polePairs=value.polePairs;
            this.speedRevolution=value.speedRevolution;
            this.armatureConductors=value.armatureConductors;
            this.flux=value.flux;
            this.parallelPaths=value.parallelPaths;
            
            return this;
        }

        public MachineEmf build(){
            return new MachineEmf(this);
        }

    
    }
    
    public double getEmf()
    {
        return this.emf;
    }
     public double getPolePairs()
    {
        return this.polePairs;
    }
     public double getspeedRevolution()
    {
        return this.speedRevolution;
    }
     public double getArmatureConductors()
    {
        return this.armatureConductors;
    }
     public double getFlux()
    {
        return this.flux;
    }
     public double getParrelPaths()
    {
        return this.parallelPaths;
    }
    public String getFormula()
    {
        return this.FORMULA;
    }
    
       
        
    }
    


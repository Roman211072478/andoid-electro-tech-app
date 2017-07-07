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
public class Tutorial implements Serializable {
    
    private Integer tutorialId;
    private String equationType;
    private String equationOutput;
    private String tutorialType;
    private String screenId;

    private Staff staff;
   //private String formula;


   
 //  private CommonCalulator calculator;
   private Equation equation;
   //private Formula formula;

   
    public Tutorial(Builder builder)
    {
        
        //default constructor        
        this.screenId = builder.screenId;
        this.tutorialType = builder.tutorialType;
        this.tutorialId = builder.tutorialId;
        this.equationOutput = builder.equationOutput;
        this.equationType = builder.equationType;
        equation = new Equation();
    
    }
    
    //builder
    public static class Builder{
        private String screenId;
        private Staff staff;
        private Integer tutorialId;
        private String equationOutput;
        private String tutorialType;
        private String equationType;
        private Equation equation = new Equation();


        public  Builder staff(Staff staffId)
        {
            this.staff = staffId;
            return this;
        }

         public  Builder screenId(String screenId)
         {
             this.screenId = screenId;
             return this;
         }
         public Builder equationType(String equationType)
        {
            this.equationType = equationType;
            return this;
        }
        public Builder tutorialId(Integer tutorialId)
        {
            this.tutorialId = tutorialId;
            return this;
        }
        public Builder equationOutput(String equationOutput)
        {
            this.equationOutput = equationOutput;
            return this;
        }
        
        public Builder tutorialType(String tutorialType)
        {
            this.tutorialType = tutorialType;
            return this;
        }
       /* public Builder equation(Equation equation)
        {
            this.equation = equation;
            return this;
        }*/
    
       
        public Builder copy(Tutorial value)
        {
            this.tutorialType = value.tutorialType;
            this.tutorialId = value.tutorialId;
            this.equationOutput = value.equationOutput;
             this.equation = value.equation;
             this.equationType = value.equationType;
             this.screenId = value.screenId;
             this.staff = value.staff;
             
             this.equation.setFormula(tutorialType, equationOutput);
           
            
            return this;
        }

        public Tutorial build(){
            
            return new Tutorial(this);
        }
    }
   
   public Integer getTutorialId()
   {
       return this.tutorialId;
   }
    public Staff getStaff()
    {
        return this.staff;
    }
   public String getEquationOutput()
   {
       return this.equationOutput;
   }
   public String getTutorialType()
   {
       return tutorialType;
   }
  
   public Equation getEquation()
   {
       return this.equation;
   }
   public String getEquationType()
   {
       return this.equationType;
   }
   
    public String getScreenId()
   {
       return this.screenId;
   }
   
    
}

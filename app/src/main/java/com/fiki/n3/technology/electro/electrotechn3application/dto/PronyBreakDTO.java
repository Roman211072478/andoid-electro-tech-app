package com.fiki.n3.technology.electro.electrotechn3application.dto;

import com.fiki.math.lab.mathlab.domain.model.PronyBreakEfficiency;

import java.io.Serializable;

/**
 * Created by Roman on 2016/04/17.
 */
public class PronyBreakDTO implements Serializable {

    private PronyBreakEfficiency pronyBreakEfficiency;
    private Integer tutorialId;    



    public PronyBreakDTO(Builder builder)
    {
       
        this.pronyBreakEfficiency = builder.pronyBreakEfficiency;
        this.tutorialId = builder.tutorialId;
       

    }
    //builder
    public static class Builder{
        private PronyBreakEfficiency pronyBreakEfficiency;
        private Integer tutorialId;
       

        
       	   
        public Builder pronyBreakEfficiency(PronyBreakEfficiency pronyBreakEfficiency)
        {
            this.pronyBreakEfficiency = pronyBreakEfficiency;
            return this;
        }
        public Builder tutorialId(Integer tutorialId)
        {
            this.tutorialId = tutorialId;
            return this;
        }

        public Builder copy(PronyBreakDTO value)
        {
            this.pronyBreakEfficiency = value.getPronyBreakEfficiency();
            this.tutorialId = value.getTutorialId();
           
            return this;
        }

        public PronyBreakDTO build(){
            return new PronyBreakDTO(this);
        }
    }

    public PronyBreakEfficiency getPronyBreakEfficiency()
    {
        return this.pronyBreakEfficiency;
    }

    public Integer getTutorialId()
    {
        return tutorialId;
    }


}

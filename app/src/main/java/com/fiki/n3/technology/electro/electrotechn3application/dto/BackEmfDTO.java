package com.fiki.n3.technology.electro.electrotechn3application.dto;

import com.fiki.math.lab.mathlab.domain.model.BackEmf;

import java.io.Serializable;

/**
 * Created by Roman on 2016/04/17.
 */
public class BackEmfDTO implements Serializable{

    private BackEmf backEmf;
    private Integer tutorialId;
    

    public BackEmfDTO(Builder builder)
    {
        this.backEmf = builder.backEmf;
        this.tutorialId = builder.tutorialId;
        
    }

    

    //builder
        public static class Builder{
        private BackEmf backEmf;
        private Integer tutorialId;
       
      

        public Builder backEmf(BackEmf backEmf)
        {
            this.backEmf = backEmf;
            return this;
        }
        public Builder tutorialId(Integer tutorialId)
        {
            this.tutorialId = tutorialId;
            return this;
        }

        public Builder copy(BackEmfDTO value)
        {
            this.backEmf = value.getBackEmf();
            this.tutorialId = value.getTutorialId();
          
            return this;
        }

        public BackEmfDTO build(){
            return new BackEmfDTO(this);
        }
    }

    public BackEmf getBackEmf()
    {
        return this.backEmf;
    }

    public Integer getTutorialId()
    {
        return tutorialId;
    }


}

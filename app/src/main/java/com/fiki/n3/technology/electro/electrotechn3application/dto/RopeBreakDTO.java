package com.fiki.n3.technology.electro.electrotechn3application.dto;

import com.fiki.math.lab.mathlab.domain.model.RopeBreakMethod;

import java.io.Serializable;

/**
 * Created by Roman on 2016/04/17.
 */
public class RopeBreakDTO  implements Serializable {

    private RopeBreakMethod ropeBreakMethod;
    private Integer tutorialId;    

    public RopeBreakDTO(Builder builder)
    {
        
        this.ropeBreakMethod = builder.ropeBreakMethod;
        this.tutorialId = builder.tutorialId;
       

    }
    //builder
    public static class Builder{
        private RopeBreakMethod ropeBreakMethod;
        private Integer tutorialId;
           	   
        public Builder ropeBreakMethod(RopeBreakMethod ropeBreakMethod)
        {
            this.ropeBreakMethod = ropeBreakMethod;
            return this;
        }
        public Builder tutorialId(Integer tutorialId)
        {
            this.tutorialId = tutorialId;
            return this;
        }

        public Builder copy(RopeBreakDTO value)
        {
            this.ropeBreakMethod = value.getRopeBreakMethod();
            this.tutorialId = value.getTutorialId();
           
            return this;
        }

        public RopeBreakDTO build(){
            return new RopeBreakDTO(this);
        }
    }

    public RopeBreakMethod getRopeBreakMethod()
    {
        return this.ropeBreakMethod;
    }

    public Integer getTutorialId()
    {
        return tutorialId;
    }


}

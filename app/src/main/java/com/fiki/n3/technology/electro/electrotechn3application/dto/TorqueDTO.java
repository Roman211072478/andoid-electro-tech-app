package com.fiki.n3.technology.electro.electrotechn3application.dto;

import com.fiki.math.lab.mathlab.domain.model.Torque;

import java.io.Serializable;

/**
 * Created by Roman on 2016/04/17.
 */
public class TorqueDTO implements Serializable {

    private Torque torque;
    private Integer tutorialId;    



    public TorqueDTO(Builder builder)
    {
        
        this.torque = builder.torque;
        this.tutorialId = builder.tutorialId;
       

    }
    //builder
    public static class Builder{
        private Torque torque;
        private Integer tutorialId;
       

       
        public Builder torque(Torque torque)
        {
            this.torque = torque;
            return this;
        }
        public Builder tutorialId(Integer tutorialId)
        {
            this.tutorialId = tutorialId;
            return this;
        }

        public Builder copy(TorqueDTO value)
        {
            this.torque = value.getTorque();
            this.tutorialId = value.getTutorialId();
            
            
            return this;
        }

        public TorqueDTO build(){
            return new TorqueDTO(this);
        }
    }

    public Torque getTorque()
    {
        return this.torque;
    }

    public Integer getTutorialId()
    {
        return tutorialId;
    }


}

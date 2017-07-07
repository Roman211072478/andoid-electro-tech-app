package com.fiki.n3.technology.electro.electrotechn3application.dto;

import com.fiki.math.lab.mathlab.domain.model.MachineEmf;

import java.io.Serializable;

/**
 * Created by Roman on 2016/04/17.
 */
public class MachineEmfDTO implements Serializable {

    private MachineEmf machineEmf;
    private Integer tutorialId;    


    public MachineEmfDTO(Builder builder)
    {
        
        this.machineEmf = builder.machineEmf;
        this.tutorialId = builder.tutorialId;
       

    }
    //builder
    public static class Builder{
        private MachineEmf machineEmf;
        private Integer tutorialId;
        
       	   
        public Builder machineEmf(MachineEmf machineEmf)
        {
            this.machineEmf = machineEmf;
            return this;
        }
        public Builder tutorialId(Integer tutorialId)
        {
            this.tutorialId = tutorialId;
            return this;
        }

        public Builder copy(MachineEmfDTO value)
        {
            this.machineEmf = value.getMachineEmf();
            this.tutorialId = value.getTutorialId();
            
            
            return this;
        }

        public MachineEmfDTO build(){
            return new MachineEmfDTO(this);
        }
    }

    public MachineEmf getMachineEmf()
    {
        return this.machineEmf;
    }

    public Integer getTutorialId()
    {
        return tutorialId;
    }


}

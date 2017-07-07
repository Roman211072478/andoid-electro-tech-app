package com.fiki.n3.technology.electro.electrotechn3application.dto;

import com.fiki.math.lab.mathlab.domain.model.SwinburneMethod;

import java.io.Serializable;

/**
 * Created by Roman on 2016/04/17.
 */
public class SwineburneDTO implements Serializable {

    private SwinburneMethod swineburne;
    private Integer tutorialId;    



    public SwineburneDTO(Builder builder)
    {
        
        this.swineburne = builder.swineburne;
        this.tutorialId = builder.tutorialId;
       

    }
    //builder
    public static class Builder{
        private SwinburneMethod swineburne;
        private Integer tutorialId;
        
       	   
        public Builder swineburne(SwinburneMethod swineburne)
        {
            this.swineburne = swineburne;
            return this;
        }
        public Builder tutorialId(Integer tutorialId)
        {
            this.tutorialId = tutorialId;
            return this;
        }

        public Builder copy(SwineburneDTO value)
        {
            this.swineburne = value.getSwineburne();
            this.tutorialId = value.getTutorialId();
            
            return this;
        }

        public SwineburneDTO build(){
            return new SwineburneDTO(this);
        }
    }

    public SwinburneMethod getSwineburne()
    {
        return this.swineburne;
    }

    public Integer getTutorialId()
    {
        return tutorialId;
    }


}

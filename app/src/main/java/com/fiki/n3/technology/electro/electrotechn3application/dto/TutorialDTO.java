package com.fiki.n3.technology.electro.electrotechn3application.dto;

import java.io.Serializable;

/**
 * Created by Roman on 2016/04/17.
 */
public class TutorialDTO implements Serializable {

    private Integer staffId;
    private String screenId;
    private Integer equationId;
    private Integer tutorialId;


    public TutorialDTO(Builder builder)
    {

        this.screenId = builder.screenId;
        this.staffId = builder.staffId;
        this.equationId = builder.equationId;
        this.tutorialId = builder.TutorialId;

    }

    //builder
    public static class Builder{
        private Integer staffId;
        private String screenId;
        private Integer equationId;
        private Integer TutorialId;





        public Builder staffId(Integer staffId)
        {
            this.staffId = staffId;
            return this;
        }
        public Builder screenId(String screenId)
        {
            this.screenId = screenId;
            return this;
        }

        public Builder equationId(Integer equationId)
        {
            this.equationId = equationId;
            return this;
        }
        public Builder TutorialId(Integer TutorialId)
        {
            this.TutorialId = TutorialId;
            return this;
        }


        public Builder copy(TutorialDTO value)
        {
            this.screenId = value.getScreenId();
            this.staffId = value.getStaffId();
            this.equationId = value.getEquationId();
            this.TutorialId = value.getTutorialId();


            return this;
        }

        public TutorialDTO build(){
            return new TutorialDTO(this);
        }


    }

    public Integer getStaffId()
    {
        return this.staffId;
    }

    public String getScreenId()
    {
        return screenId;
    }


    public Integer getEquationId()
    {
        return this.equationId;
    }
    public Integer getTutorialId()
    {
        return this.tutorialId;
    }

}

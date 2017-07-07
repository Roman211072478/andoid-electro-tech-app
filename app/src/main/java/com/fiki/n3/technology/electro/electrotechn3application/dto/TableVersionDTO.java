package com.fiki.n3.technology.electro.electrotechn3application.dto;

import java.io.Serializable;

/**
 * Created by Roman on 2016/06/15.
 */
public class TableVersionDTO implements Serializable {

    private String type = "version";

    public Integer getVersion() {
        return version;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    private Integer version;
    private Integer id;

    public TableVersionDTO(Builder builder)
    {
        this.version = builder.version;
        this.id = builder.id;
    }
    //builder
    public static class Builder{
        private Integer version;
        private Integer id;
        private String type = "version";

        public Builder id(Integer id)
        {
            this.id = id;
            return this;
        }
        public Builder version(Integer version)
        {
            this.version = version;
            return this;
        }

        public Builder copy(TableVersionDTO value)
        {
            this.version = value.getVersion();
            this.id = value.getId();
            this.type = value.getType();

            return this;
        }

        public TableVersionDTO build(){
            return new TableVersionDTO(this);
        }
    }

}

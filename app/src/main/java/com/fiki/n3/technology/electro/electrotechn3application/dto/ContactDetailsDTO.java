package com.fiki.n3.technology.electro.electrotechn3application.dto;

import com.fiki.math.lab.mathlab.domain.model.ContactDetails;

import java.io.Serializable;

/**
 * Created by Roman on 2016/04/17.
 */
public class ContactDetailsDTO  implements Serializable {

    private Integer staffId;
    private ContactDetails contactDetails;

    public ContactDetailsDTO(Builder builder)
    {
       
        this.contactDetails = builder.contactDetails;
        this.staffId = builder.staffId;
       

    }
    //builder
    public static class Builder{
        private ContactDetails contactDetails;
        private Integer staffId;
       
        
        public Builder contactDetails(ContactDetails contactDetails)
        {
            this.contactDetails = contactDetails;
            return this;
        }
        public Builder staffId(Integer staffId)
        {
            this.staffId = staffId;
            return this;
        }

        public Builder copy(ContactDetailsDTO value)
        {
            this.contactDetails = value.getContactDetails();
            this.staffId = value.getStaffId();
           
            return this;
        }

        public ContactDetailsDTO build(){
            return new ContactDetailsDTO(this);
        }
    }

    public ContactDetails getContactDetails()
    {
        return this.contactDetails;
    }

    public Integer getStaffId()
    {
        return staffId;
    }


}

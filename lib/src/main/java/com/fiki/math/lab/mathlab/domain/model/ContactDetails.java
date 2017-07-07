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
public class ContactDetails implements Serializable {
    
    private String emailAddress;
    private String cellNumber;
    
   /* public ContactDetails()
    {}*/
    public ContactDetails(String emailAddress,String cellNumber)
    {
        this.cellNumber = cellNumber;
        this.emailAddress = emailAddress;
    }
    
    public void setCellNumber(String cellNumber)
    {
        this.cellNumber = cellNumber;
    }
    public void setEmailAddres(String EmailAddress)
    {
        this.emailAddress = EmailAddress;
    }
    
    public String getEmail()
    {
        return this.emailAddress;
    }
    public String getCellNumber()
    {
        return this.cellNumber;
    }
}

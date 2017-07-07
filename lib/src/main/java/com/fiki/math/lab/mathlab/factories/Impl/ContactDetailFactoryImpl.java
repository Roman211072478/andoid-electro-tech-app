/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.factories.Impl;

import com.fiki.math.lab.mathlab.domain.model.ContactDetails;
import com.fiki.math.lab.mathlab.factories.ContactDetailsFactory;

/**
 *
 * @author Roman
 */
public class  ContactDetailFactoryImpl implements ContactDetailsFactory{

     private static ContactDetailFactoryImpl factory = null;

    private  ContactDetailFactoryImpl() {
    }
    
    public static ContactDetailFactoryImpl getInstance(){
        if(factory ==null)
            factory = new ContactDetailFactoryImpl();
        return factory;
    }

    
  
    @Override
    public ContactDetails createContactDetails(String emailAddress, String cellNumber) {
          ContactDetails contactDeatils = new ContactDetails(emailAddress,cellNumber);
          return contactDeatils;
    }
    
}

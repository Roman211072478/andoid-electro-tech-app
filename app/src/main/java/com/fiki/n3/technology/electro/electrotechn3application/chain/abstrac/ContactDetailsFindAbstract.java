package com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac;


import com.fiki.n3.technology.electro.electrotechn3application.dto.ContactDetailsDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/29.
 */
public abstract class ContactDetailsFindAbstract {
	
	 public ContactDetailsFindAbstract nextValue;

    public void setNext(ContactDetailsFindAbstract nextValue) {
        this.nextValue = nextValue;
    }
    
    //This function will be overidded to all subClasses
    public abstract HashMap action(String request, ContactDetailsDTO ContactDetailsDTO, ContextDTO ctx);
    
	
}

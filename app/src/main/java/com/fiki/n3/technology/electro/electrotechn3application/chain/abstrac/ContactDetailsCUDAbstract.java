package com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac;

import com.fiki.n3.technology.electro.electrotechn3application.dto.ContactDetailsDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public abstract class ContactDetailsCUDAbstract {
	
	 public ContactDetailsCUDAbstract nextValue;

    public void setNext(ContactDetailsCUDAbstract nextValue) {
        this.nextValue = nextValue;
    }
    
    //This function will be overidded to all subClasses
    public abstract String action(String request, ContactDetailsDTO contactDetailsDTO, ContextDTO ctx);
    
	
}
package com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac;


import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.LoginDetailsDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public abstract class LoginDetailsCUDAbstract {
	
	 public LoginDetailsCUDAbstract nextValue;

    public void setNext(LoginDetailsCUDAbstract nextValue) {
        this.nextValue = nextValue;
    }
    
    //This function will be overidded to all subClasses
    public abstract String action(String request, LoginDetailsDTO loginDetailsDTO, ContextDTO contextDTO);
    
	
}
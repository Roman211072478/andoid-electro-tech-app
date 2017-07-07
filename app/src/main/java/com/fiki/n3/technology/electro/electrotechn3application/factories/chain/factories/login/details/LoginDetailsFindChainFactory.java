package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.login.details;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.login.details.LoginDetailsFindAllService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.login.details.LoginDetailsFindByUsernameService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.LoginDetailsFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.LoginDetailsDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class LoginDetailsFindChainFactory {

    public static HashMap performRequest(String request, LoginDetailsDTO loginDetailsDTO, ContextDTO ctx){
        LoginDetailsFindAbstract chain = setUpChain();
        return chain.action(request,loginDetailsDTO,ctx);
    }

    public static LoginDetailsFindAbstract setUpChain(){

        LoginDetailsFindAbstract a = new LoginDetailsFindByUsernameService();
        LoginDetailsFindAbstract b = new LoginDetailsFindAllService();

        a.setNext(b);

        return a;
    }
}

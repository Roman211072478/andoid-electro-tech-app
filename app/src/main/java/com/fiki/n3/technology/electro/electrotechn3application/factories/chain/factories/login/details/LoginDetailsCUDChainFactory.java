package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.login.details;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.login.details.LoginDetailsCreateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.login.details.LoginDetailsDeleteService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.login.details.LoginDetailsDeleteTableService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.login.details.LoginDetailsUpdateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.LoginDetailsCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.LoginDetailsDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public class LoginDetailsCUDChainFactory {

    public static String performRequest(String request, LoginDetailsDTO loginDetailsDTO, ContextDTO ctx){
        LoginDetailsCUDAbstract chain = setUpChain();
        return chain.action(request,loginDetailsDTO,ctx);
    }

    public static LoginDetailsCUDAbstract setUpChain(){

        LoginDetailsCUDAbstract a = new LoginDetailsCreateService();
        LoginDetailsCUDAbstract b = new LoginDetailsDeleteService();
        LoginDetailsCUDAbstract c = new LoginDetailsUpdateService();
        LoginDetailsCUDAbstract d = new LoginDetailsDeleteTableService();

        a.setNext(b);
        b.setNext(c);
        c.setNext(d);

        return a;
    }

}

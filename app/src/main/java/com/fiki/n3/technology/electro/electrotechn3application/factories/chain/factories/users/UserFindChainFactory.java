package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.users;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.user.UserFindAllService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.user.UserFindByIdService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.user.UserFindLoginIdService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.UserFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.UserDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class UserFindChainFactory {

    public static HashMap performRequest(String request, UserDTO userDTO, ContextDTO ctx){
        UserFindAbstract chain = setUpChain();
        return chain.action(request,userDTO ,ctx);
    }

    public static UserFindAbstract setUpChain(){

        UserFindAbstract a = new UserFindByIdService();
        UserFindAbstract b = new UserFindAllService();
        UserFindAbstract c = new UserFindLoginIdService();

        a.setNext(b);
        b.setNext(c);

        return a;
    }
}

package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.users;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.user.UserCreateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.user.UserDeleteService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.user.UserDeleteTableService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.user.UserUpdateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.UserCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.UserDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public class UserCUDChainFactory {

    public static String performRequest(String request, UserDTO userDTO, ContextDTO ctx){
        UserCUDAbstract chain = setUpChain();
        return chain.action(request,userDTO,ctx);
    }

    public static UserCUDAbstract setUpChain(){

        UserCUDAbstract a = new UserCreateService();
        UserCUDAbstract b = new UserDeleteService();
        UserCUDAbstract c = new UserUpdateService();
        UserCUDAbstract d = new UserDeleteTableService();

        a.setNext(b);
        b.setNext(c);
        c.setNext(d);

        return a;
    }

}

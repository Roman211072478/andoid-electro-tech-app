package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.role;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.role.RoleFindAllService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.role.RoleFindByIdService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.RoleFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.RoleDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class RoleFindChainFactory {

    public static HashMap performRequest(String request, RoleDTO roleDTO, ContextDTO ctx){
        RoleFindAbstract chain = setUpChain();
        return chain.action(request,roleDTO,ctx);
    }

    public static RoleFindAbstract setUpChain(){

        RoleFindAbstract a = new RoleFindByIdService();
        RoleFindAbstract b = new RoleFindAllService();

        a.setNext(b);

        return a;
    }
}

package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.role;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.role.RoleCreateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.role.RoleDeleteService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.role.RoleDeleteTableService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.role.RoleUpdateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.RoleCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.RoleDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public class RoleCUDChainFactory {

    public static String performRequest(String request, RoleDTO roleDTO, ContextDTO ctx){
        RoleCUDAbstract chain = setUpChain();
        return chain.action(request,roleDTO,ctx);
    }

    public static RoleCUDAbstract setUpChain(){

        RoleCUDAbstract a = new RoleCreateService();
        RoleCUDAbstract b = new RoleDeleteService();
        RoleCUDAbstract c = new RoleUpdateService();
        RoleCUDAbstract d = new RoleDeleteTableService();

        a.setNext(b);
        b.setNext(c);
        c.setNext(d);

        return a;
    }

}

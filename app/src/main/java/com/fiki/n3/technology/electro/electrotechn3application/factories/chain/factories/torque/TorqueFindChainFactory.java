package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.torque;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.torque.TorqueFindAllService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.torque.TorqueFindByIdService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.TorqueFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TorqueDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class TorqueFindChainFactory {

    public static HashMap performRequest(String request, TorqueDTO torqueDTO, ContextDTO ctx){
        TorqueFindAbstract chain = setUpChain();
        return chain.action(request,torqueDTO,ctx);
    }

    public static TorqueFindAbstract setUpChain(){

        TorqueFindAbstract a = new TorqueFindByIdService();
        TorqueFindAbstract b = new TorqueFindAllService();

        a.setNext(b);

        return a;
    }
}

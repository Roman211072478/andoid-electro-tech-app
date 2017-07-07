package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.torque;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.torque.TorqueCreateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.torque.TorqueDeleteService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.torque.TorqueDeleteTableService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.torque.TorqueUpdateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.TorqueCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TorqueDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public class TorqueCUDChainFactory {

    public static String performRequest(String request, TorqueDTO torqueDTO, ContextDTO ctx){
        TorqueCUDAbstract chain = setUpChain();
        return chain.action(request,torqueDTO,ctx);
    }

    public static TorqueCUDAbstract setUpChain(){

        TorqueCUDAbstract a = new TorqueCreateService();
        TorqueCUDAbstract b = new TorqueDeleteService();
        TorqueCUDAbstract c = new TorqueUpdateService();
        TorqueCUDAbstract d = new TorqueDeleteTableService();

        a.setNext(b);
        b.setNext(c);
        c.setNext(d);

        return a;
    }

}

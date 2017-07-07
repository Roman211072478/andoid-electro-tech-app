package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.equation;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.equation.EquationFindAllService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.equation.EquationFindByIdService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.EquationFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.EquationDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class EquationFindChainFactory {

    public static HashMap performRequest(String request, EquationDTO equationDTO, ContextDTO ctx){
        EquationFindAbstract chain = setUpChain();
        return chain.action(request,equationDTO,ctx);
    }

    public static EquationFindAbstract setUpChain(){

        EquationFindAbstract a = new EquationFindByIdService();
        EquationFindAbstract b = new EquationFindAllService();

        a.setNext(b);

        return a;
    }
}

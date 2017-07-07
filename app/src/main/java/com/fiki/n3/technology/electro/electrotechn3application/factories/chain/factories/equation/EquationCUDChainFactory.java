package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.equation;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.equation.EquationCreateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.equation.EquationDeleteService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.equation.EquationDeleteTableService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.equation.EquationUpdateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.EquationCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.EquationDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public class EquationCUDChainFactory {

    public static String performRequest(String request, EquationDTO equationDTO, ContextDTO ctx){
        EquationCUDAbstract chain = setUpChain();
        return chain.action(request,equationDTO,ctx);
    }

    public static EquationCUDAbstract setUpChain(){

        EquationCUDAbstract a = new EquationCreateService();
        EquationCUDAbstract b = new EquationDeleteService();
        EquationCUDAbstract c = new EquationUpdateService();
        EquationCUDAbstract d = new EquationDeleteTableService();

        a.setNext(b);
        b.setNext(c);
        c.setNext(d);

        return a;
    }

}

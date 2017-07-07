package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.swineburne;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.swineburne.SwineburneCreateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.swineburne.SwineburneDeleteService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.swineburne.SwineburneDeleteTableService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.swineburne.SwineburneUpdateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.SwineburneCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.SwineburneDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public class SwineburneCUDChainFactory {

    public static String performRequest(String request, SwineburneDTO swineburneDTO, ContextDTO ctx){
        SwineburneCUDAbstract chain = setUpChain();
        return chain.action(request,swineburneDTO,ctx);
    }

    public static SwineburneCUDAbstract setUpChain(){

        SwineburneCUDAbstract a = new SwineburneCreateService();
        SwineburneCUDAbstract b = new SwineburneDeleteService();
        SwineburneCUDAbstract c = new SwineburneUpdateService();
        SwineburneCUDAbstract d = new SwineburneDeleteTableService();

        a.setNext(b);
        b.setNext(c);
        c.setNext(d);

        return a;
    }

}

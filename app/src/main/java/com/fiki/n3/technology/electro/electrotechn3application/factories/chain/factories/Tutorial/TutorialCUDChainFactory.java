package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.Tutorial;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.tutorial.TutorialCreateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.tutorial.TutorialDeleteService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.tutorial.TutorialDeleteTableService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.tutorial.TutorialUpdateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.TutorialCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TutorialDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public class TutorialCUDChainFactory {

    public static String performRequest(String request, TutorialDTO tutorialDTO, ContextDTO ctx){
        TutorialCUDAbstract chain = setUpChain();
        return chain.action(request,tutorialDTO,ctx);
    }

    public static TutorialCUDAbstract setUpChain(){

        TutorialCUDAbstract a = new TutorialCreateService();
        TutorialCUDAbstract b = new TutorialDeleteService();
        TutorialCUDAbstract c = new TutorialUpdateService();
        TutorialCUDAbstract d = new TutorialDeleteTableService();

        a.setNext(b);
        b.setNext(c);
        c.setNext(d);

        return a;
    }

}

package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.tutorial;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.TutorialDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.TutorialDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.TutorialCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TutorialDTO;

/**
 * Created by Roman on 2016/04/30.
 */
public class TutorialUpdateService extends TutorialCUDAbstract {

    
    @Override
    public String action(String request, TutorialDTO tutorialDTO, ContextDTO ctx) {


        if(request.equals("update"))
        {
            //put DAO here, and return result
            TutorialDAO dao = new TutorialDAOImpl(ctx.getContext());
            int result = dao.update(tutorialDTO);

            return String.valueOf(result) ;
        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  tutorialDTO,ctx);
            }
            return "invalid";
        }

    }
}

package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.tutorial;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.TutorialDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.TutorialDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.TutorialCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TutorialDTO;

/**
 * Created by Roman on 2016/04/30.
 */
public class TutorialDeleteTableService extends TutorialCUDAbstract {

    @Override
    public String action(String request, TutorialDTO tutorialDTO, ContextDTO ctx) {


        if(request.equals("drop"))
        {
            //put DAO here, and return result
            TutorialDAO dao = new TutorialDAOImpl(ctx.getContext());

            //if one use all the CRUD methods and return a object, this
            //will decrese errors
            int result = dao.deleteTable();


            return String.valueOf(result);

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  tutorialDTO,ctx);
            }
            return "invalid";//on calling side, check if this is found first,then error or successfull
        }

    }
}

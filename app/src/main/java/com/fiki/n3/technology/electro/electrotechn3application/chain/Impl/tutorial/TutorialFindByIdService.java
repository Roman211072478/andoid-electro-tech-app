package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.tutorial;

import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.TutorialDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.TutorialDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.TutorialFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TutorialDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class TutorialFindByIdService extends TutorialFindAbstract {

    @Override
    public HashMap action(String request, TutorialDTO tutorialDTO, ContextDTO ctx) {
        if(request.equals("find by id"))
        {

            TutorialDTO result;
            //put DAO here, and return result
            TutorialDAO dao = new TutorialDAOImpl(ctx.getContext());
            result = dao.findByIdTutorialId(tutorialDTO.getTutorialId());



            HashMap map = new HashMap();
            map.put("request", request);
            map.put("result", result);
            return map;

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  tutorialDTO,ctx);
            }
            HashMap map = new HashMap();
            map.put("request","invalid");

            return map;
        }
    }


}

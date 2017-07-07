package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.rope.breakmethod;

import com.fiki.math.lab.mathlab.domain.model.RopeBreakMethod;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.RopeBreakDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.RopeBreakDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.RopeBreakFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.RopeBreakDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class RopeBreakFindByIdService extends RopeBreakFindAbstract {

    @Override
    public HashMap action(String request, RopeBreakDTO ropeBreakDTO, ContextDTO ctx) {
        if(request.equals("find by id"))
        {

            RopeBreakMethod result;
            //put DAO here, and return result
            RopeBreakDAO dao = new RopeBreakDAOImpl(ctx.getContext());
            result = dao.findByIdTutorialId(ropeBreakDTO.getTutorialId());



            HashMap map = new HashMap();
            map.put("request", request);
            map.put("result", result);
            return map;

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  ropeBreakDTO,ctx);
            }
            HashMap map = new HashMap();
            map.put("request","invalid");

            return map;
        }
    }


}

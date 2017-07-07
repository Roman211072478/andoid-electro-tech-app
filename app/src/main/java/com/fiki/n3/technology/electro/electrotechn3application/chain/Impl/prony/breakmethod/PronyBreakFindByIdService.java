package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.prony.breakmethod;

import com.fiki.math.lab.mathlab.domain.model.PronyBreakEfficiency;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.PronyBreakDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.PronyBreakDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.PronyBreakFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.PronyBreakDTO;


import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class PronyBreakFindByIdService extends PronyBreakFindAbstract {

    @Override
    public HashMap action(String request, PronyBreakDTO pronyBreakDTO, ContextDTO ctx) {
        if(request.equals("find by id"))
        {

            PronyBreakEfficiency result;
            //put DAO here, and return result
            PronyBreakDAO dao = new PronyBreakDAOImpl(ctx.getContext());
            result = dao.findByIdTutorialId(pronyBreakDTO.getTutorialId());

            HashMap map = new HashMap();
            map.put("request", request);
            map.put("result", result);
            return map;

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  pronyBreakDTO,ctx);
            }
            HashMap map = new HashMap();
            map.put("request","invalid");

            return map;
        }
    }


}

package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.torque;

import com.fiki.math.lab.mathlab.domain.model.Torque;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.TorqueDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.TorqueDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.TorqueFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TorqueDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class TorqueFindByIdService extends TorqueFindAbstract {

    @Override
    public HashMap action(String request, TorqueDTO torqueDTO, ContextDTO ctx) {
        if(request.equals("find by id"))
        {

            Torque result;
            //put DAO here, and return result
            TorqueDAO dao = new TorqueDAOImpl(ctx.getContext());
            result = dao.findByIdTutorialId(torqueDTO.getTutorialId());



            HashMap map = new HashMap();
            map.put("request", request);
            map.put("result", result);
            return map;

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  torqueDTO,ctx);
            }
            HashMap map = new HashMap();
            map.put("request","invalid");

            return map;
        }
    }


}

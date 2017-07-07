package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.torque;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.TorqueDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.TorqueDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.TorqueCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.TorqueDTO;

/**
 * Created by Roman on 2016/04/30.
 */
public class TorqueUpdateService extends TorqueCUDAbstract {

    
    @Override
    public String action(String request, TorqueDTO torqueDTO, ContextDTO ctx) {


        if(request.equals("update"))
        {
            //put DAO here, and return result
            TorqueDAO dao = new TorqueDAOImpl(ctx.getContext());
            int result = dao.update(torqueDTO.getTorque(), torqueDTO.getTutorialId());

            return String.valueOf(result) ;


        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  torqueDTO,ctx);
            }
            return "invalid";
        }

    }
}

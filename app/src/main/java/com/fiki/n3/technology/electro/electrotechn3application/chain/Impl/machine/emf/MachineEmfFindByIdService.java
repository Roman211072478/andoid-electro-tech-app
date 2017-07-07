package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.machine.emf;

import com.fiki.math.lab.mathlab.domain.model.MachineEmf;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.MachineEmfDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.MachineEmfDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.MachineEmfFindAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.MachineEmfDTO;

import java.util.HashMap;

/**
 * Created by Roman on 2016/04/30.
 */
public class MachineEmfFindByIdService extends MachineEmfFindAbstract {

    @Override
    public HashMap action(String request, MachineEmfDTO machineEmfDTO, ContextDTO ctx) {
        if(request.equals("find by id"))
        {

            MachineEmf result;
            //put DAO here, and return result
            MachineEmfDAO dao = new MachineEmfDAOImpl(ctx.getContext());
            result = dao.findByIdTutorialId(machineEmfDTO.getTutorialId());



            HashMap map = new HashMap();
            map.put("request", request);
            map.put("result", result);
            return map;

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  machineEmfDTO,ctx);
            }
            HashMap map = new HashMap();
            map.put("request","invalid");

            return map;
        }
    }


}

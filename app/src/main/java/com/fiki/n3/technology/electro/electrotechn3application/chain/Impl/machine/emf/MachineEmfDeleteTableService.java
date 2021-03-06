package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.machine.emf;

import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.MachineEmfDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.MachineEmfDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.MachineEmfCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.MachineEmfDTO;

/**
 * Created by Roman on 2016/04/30.
 */
public class MachineEmfDeleteTableService extends MachineEmfCUDAbstract {

    @Override
    public String action(String request, MachineEmfDTO machineEmfDTO, ContextDTO ctx) {


        if(request.equals("drop"))
        {
            //put DAO here, and return result
            MachineEmfDAO dao = new MachineEmfDAOImpl(ctx.getContext());
            int result = dao.deleteTable();

            return String.valueOf(result);

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  machineEmfDTO,ctx);
            }
            return "invalid";//on calling side, check if this is found first,then error or successfull
        }

    }
}

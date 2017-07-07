package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.back.emf;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.BackEmfDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.BackEmfDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.BackEmfCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.BackEmfDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;

/**
 * Created by Roman on 2016/04/30.
 */
public class BackEmfDeleteTableService extends BackEmfCUDAbstract {

    @Override
    public String action(String request, BackEmfDTO backEmfDTO, ContextDTO contextDTO) {


        if(request.equals("drop"))
        {
            //put DAO here, and return result
            BackEmfDAO dao = new BackEmfDAOImpl(contextDTO.getContext());
            int result = dao.deleteTable();

            return String.valueOf(result);

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  backEmfDTO,contextDTO);
            }
            return "invalid";//on calling side, check if this is found first,then error or successfull
        }

    }
}

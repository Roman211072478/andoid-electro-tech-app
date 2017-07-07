package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.back.emf;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.BackEmfDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.BackEmfDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.BackEmfCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.BackEmfDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;

/**
 * Created by Roman on 2016/04/30.
 */
public class BackEmfDeleteService extends BackEmfCUDAbstract {
    private BackEmfDAO dao;
    @Override
    public String action(String request, BackEmfDTO backEmfDTO, ContextDTO contextDTO) {


        if(request.equals("delete"))
        {
            //put DAO here, and return result
            BackEmfDAO dao = new BackEmfDAOImpl(contextDTO.getContext());
            int result = dao.delete(backEmfDTO.getTutorialId());

            return String.valueOf(result) ;

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  backEmfDTO,contextDTO);
            }
            return "invalid";
        }

    }
}

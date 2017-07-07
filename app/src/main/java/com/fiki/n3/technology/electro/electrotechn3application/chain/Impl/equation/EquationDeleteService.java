package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.equation;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.EquationDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.EquationDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.EquationCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.EquationDTO;

/**
 * Created by Roman on 2016/04/30.
 */
public class EquationDeleteService extends EquationCUDAbstract {
    
    @Override
    public String action(String request, EquationDTO equationDTO, ContextDTO ctx) {
	

        if(request.equals("delete"))
        {
            //put DAO here, and return result
            EquationDAO dao = new EquationDAOImpl(ctx.getContext());
            int result = dao.delete(equationDTO.getEquation().getEquationId());

            return String.valueOf(result);            
        }
        else{

            if (nextValue != null) 
            {
                return nextValue.action( request,  equationDTO,ctx);
            }
            return "invalid";//on calling side, check if this is found first,then error or successfull
        }

    }
}

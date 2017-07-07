package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.equation;

import com.fiki.math.lab.mathlab.domain.model.Equation;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.EquationDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.EquationDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.EquationCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.EquationDTO;

/**
 * Created by Roman on 2016/04/30.
 */
public class EquationUpdateService extends EquationCUDAbstract {

     @Override
    public String action(String request, EquationDTO equationDetailsDTO, ContextDTO ctx) {
	

        if(request.equals("update"))
        {
            //put DAO here, and return result
            EquationDAO dao = new EquationDAOImpl(ctx.getContext());
            Equation equation = new Equation();
            equation = equationDetailsDTO.getEquation();

            equation.setOutput(equationDetailsDTO.getEquationOutput());
            equation.setType(equationDetailsDTO.getEquationType());


            Long result = dao.create(equation);

            return String.valueOf(result);            
        }
        else{

            if (nextValue != null) 
            {
                return nextValue.action( request,  equationDetailsDTO,ctx);
            }
            return "invalid";//on calling side, check if this is found first,then error or successfull
        }

    }
}

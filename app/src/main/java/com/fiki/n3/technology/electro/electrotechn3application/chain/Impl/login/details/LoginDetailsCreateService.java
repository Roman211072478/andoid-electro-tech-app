package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.login.details;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.LoginDetailsDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.LoginDetailsDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.LoginDetailsCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.LoginDetailsDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public class LoginDetailsCreateService extends LoginDetailsCUDAbstract {


	  @Override
    public String action(String request, LoginDetailsDTO loginDetailsDTO, ContextDTO contextDTO) {
	

        if(request.equals("create"))
        {
            //put DAO here, and return result
            LoginDetailsDAO dao = new LoginDetailsDAOImpl(contextDTO.getContext());
            Long result = dao.create(loginDetailsDTO.getLoginDetails());

            return String.valueOf(result);            
        }
        else{

            if (nextValue != null) 
            {
                return nextValue.action( request,  loginDetailsDTO,contextDTO);
            }
            return "invalid";//on calling side, check if this is found first,then error or successfull
        }

    }
}

package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.role;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.RoleDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.RoleDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.RoleCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.RoleDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public class RoleCreateService extends RoleCUDAbstract {


	  @Override
    public String action(String request, RoleDTO roleDTO, ContextDTO ctx) {
	

        if(request.equals("create"))
        {
            //put DAO here, and return result
            RoleDAO dao = new RoleDAOImpl(ctx.getContext());
            Long result = dao.create(roleDTO.getRole());

            return String.valueOf(result);
            
        }
        else{

            if (nextValue != null) 
            {
                return nextValue.action( request,  roleDTO,ctx);
            }
            return "invalid";//on calling side, check if this is found first,then error or successfull
        }

    }


}

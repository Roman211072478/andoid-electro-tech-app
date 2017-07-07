package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.staff;

import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.StaffDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.StaffDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.StaffCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.StaffDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public class StaffCreateService extends StaffCUDAbstract {


	  @Override
    public String action(String request, StaffDTO staffDTO, ContextDTO ctx) {
	

        if(request.equals("create"))
        {
            //put DAO here, and return result
            StaffDAO dao = new StaffDAOImpl(ctx.getContext());
            Long result = dao.create(staffDTO);

            return String.valueOf(result);
            
        }
        else{

            if (nextValue != null) 
            {
                return nextValue.action( request,  staffDTO,ctx);
            }
            return "invalid";//on calling side, check if this is found first,then error or successfull
        }

    }
}

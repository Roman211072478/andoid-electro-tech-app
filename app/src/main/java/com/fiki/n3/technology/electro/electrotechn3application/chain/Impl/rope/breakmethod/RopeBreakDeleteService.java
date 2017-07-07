package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.rope.breakmethod;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.RopeBreakDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.RopeBreakDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.RopeBreakCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.RopeBreakDTO;

/**
 * Created by Roman on 2016/04/30.
 */
public class RopeBreakDeleteService extends RopeBreakCUDAbstract {
    
	
    @Override
    public String action(String request, RopeBreakDTO ropeBreakDTO, ContextDTO ctx) {


        if(request.equals("delete"))
        {
            //put DAO here, and return result
            RopeBreakDAO dao = new RopeBreakDAOImpl(ctx.getContext());
            int result = dao.delete(ropeBreakDTO.getTutorialId());

            return String.valueOf(result) ;

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  ropeBreakDTO,ctx);
            }
            return "invalid";
        }

    }
}

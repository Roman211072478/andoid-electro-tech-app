package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.prony.breakmethod;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.PronyBreakDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.PronyBreakDAO;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.PronyBreakCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.PronyBreakDTO;

/**
 * Created by Roman on 2016/04/30.
 */
public class PronyBreakDeleteService extends PronyBreakCUDAbstract {
    
	
    @Override
    public String action(String request, PronyBreakDTO pronyBreakDTO, ContextDTO ctx) {


        if(request.equals("delete"))
        {
            //put DAO here, and return result
            PronyBreakDAO dao = new PronyBreakDAOImpl(ctx.getContext());
            int result = dao.delete(pronyBreakDTO.getTutorialId());

            return String.valueOf(result) ;

        }
        else{

            if (nextValue != null)
            {
                return nextValue.action( request,  pronyBreakDTO,ctx);
            }
            return "invalid";
        }

    }
}

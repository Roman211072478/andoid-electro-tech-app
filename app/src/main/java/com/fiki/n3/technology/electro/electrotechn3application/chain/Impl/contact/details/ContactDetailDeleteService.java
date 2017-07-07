package com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.contact.details;


import com.fiki.n3.technology.electro.electrotechn3application.Impl.ContactDetailsDAO;
import com.fiki.n3.technology.electro.electrotechn3application.Impl.Impl.ContactDetailsDAOImpl;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.ContactDetailsCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContactDetailsDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;

/**
 * Created by Roman on 2016/04/30.
 */
public class ContactDetailDeleteService extends ContactDetailsCUDAbstract {
    
    @Override
    public String action(String request, ContactDetailsDTO contactDetailsDTO, ContextDTO ctx) {
	

        if(request.equals("delete"))
        {
            //put DAO here, and return result
            ContactDetailsDAO dao = new ContactDetailsDAOImpl(ctx.getContext());
            int result = dao.delete(contactDetailsDTO.getStaffId());

            return String.valueOf(result);            
        }
        else{

            if (nextValue != null) 
            {
                return nextValue.action( request,  contactDetailsDTO,ctx);
            }
            return "invalid";//on calling side, check if this is found first,then error or successfull
        }

    }
}

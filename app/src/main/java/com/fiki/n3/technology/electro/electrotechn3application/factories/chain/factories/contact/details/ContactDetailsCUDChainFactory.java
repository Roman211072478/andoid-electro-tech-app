package com.fiki.n3.technology.electro.electrotechn3application.factories.chain.factories.contact.details;


import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.contact.details.ContactDetailDeleteService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.contact.details.ContactDetailDeleteTableService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.contact.details.ContactDetailUpdateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.Impl.contact.details.ContactDetailsCreateService;
import com.fiki.n3.technology.electro.electrotechn3application.chain.abstrac.ContactDetailsCUDAbstract;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContactDetailsDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ContextDTO;

/**
 * Created by Roman on 2016/04/29.
 */
public class ContactDetailsCUDChainFactory {

    public static String performRequest(String request, ContactDetailsDTO contactDetailsDTO, ContextDTO ctx){
        ContactDetailsCUDAbstract chain = setUpChain();
        return chain.action(request,contactDetailsDTO,ctx);
    }

    public static ContactDetailsCUDAbstract setUpChain(){

        ContactDetailsCUDAbstract a = new ContactDetailsCreateService();
        ContactDetailsCUDAbstract b = new ContactDetailDeleteService();
        ContactDetailsCUDAbstract c = new ContactDetailUpdateService();
        ContactDetailsCUDAbstract d = new ContactDetailDeleteTableService();

        a.setNext(b);
        b.setNext(c);
        c.setNext(d);

        return a;
    }

}

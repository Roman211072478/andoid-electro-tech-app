package com.fiki.n3.technology.electro.electrotechn3application.services.equation;

import android.content.Context;
import android.content.Intent;

import com.fiki.n3.technology.electro.electrotechn3application.dto.EquationDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;

/**
 * Created by Roman on 2016/05/19.
 */
public class EquationServiceRunner {

    private Context ctx;

    public EquationServiceRunner(Context ctx) {
        this.ctx = ctx;
    }

    private void findService(EquationDTO dto, ServiceResultReceiver receiver, String request)
    {
        Intent service = new Intent(ctx, EquationFindService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", request);
        service.putExtra("dtoTag", dto);
        service.putExtra("receiverTag", receiver);

        ctx.startService(service);
    }
    private void cudService(EquationDTO dto, ServiceResultReceiver receiver,String request)
    {
        Intent service = new Intent(ctx, EquationCUDService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", request);
        service.putExtra("dtoTag", dto);
        service.putExtra("receiverTag", receiver);

        ctx.startService(service);
    }

    public void findAllService( EquationDTO dto, ServiceResultReceiver receiver)
    {
        findService(dto, receiver, "find all");
    }

    public void findByIdService( EquationDTO dto, ServiceResultReceiver receiver)
    {
        findService(dto, receiver, "find by id");
    }

    public void createService(EquationDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "create");
    }

    public void DeleteService(EquationDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "delete");
    }
    public void updateService(EquationDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "update");
    }
    public void dropTableService(EquationDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "drop");
    }

}

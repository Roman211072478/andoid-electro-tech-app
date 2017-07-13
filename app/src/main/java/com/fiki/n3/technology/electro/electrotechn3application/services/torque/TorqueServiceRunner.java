package com.fiki.n3.technology.electro.electrotechn3application.services.torque;

import android.content.Context;
import android.content.Intent;

import com.fiki.n3.technology.electro.electrotechn3application.dto.TorqueDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;

/**
 * Created by Roman on 2016/05/26.
 */
public class TorqueServiceRunner {

    private Context ctx;

    public TorqueServiceRunner(Context ctx) {
        this.ctx = ctx;
    }

    private void findService(TorqueDTO dto, ServiceResultReceiver receiver, String request)
    {
        Intent service = new Intent(ctx, TorqueFindService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", request);
        service.putExtra("dtoTag", dto);
        service.putExtra("receiverTag", receiver);

        ctx.startService(service);
    }
    private void cudService(TorqueDTO dto, ServiceResultReceiver receiver,String request)
    {
        Intent service = new Intent(ctx, TorqueCUDService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", request);
        service.putExtra("dtoTag", dto);
        service.putExtra("receiverTag", receiver);

        ctx.startService(service);
    }

    public void findAllService( TorqueDTO dto, ServiceResultReceiver receiver)
    {
        findService(dto, receiver, "find all");
    }

    public void findByIdService( TorqueDTO dto, ServiceResultReceiver receiver)
    {
        findService(dto, receiver, "find by id");
    }

    public void createService(TorqueDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto,receiver,"create");
    }

    public void DeleteService(TorqueDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "delete");
    }
    public void updateService(TorqueDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "update");
    }
    public void dropTableService(TorqueDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "drop");
    }
}

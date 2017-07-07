package com.fiki.n3.technology.electro.electrotechn3application.services.back.emf;

import android.content.Context;
import android.content.Intent;

import com.fiki.roman.andoirdmathlab.dto.BackEmfDTO;
import com.fiki.roman.andoirdmathlab.receiver.ServiceResultReceiver;

/**
 * Created by Roman on 2016/05/20.
 */
public class BackEmfServiceRunner {

    private Context ctx;

    public BackEmfServiceRunner(Context ctx) {
        this.ctx = ctx;
    }

    private void findService(BackEmfDTO dto, ServiceResultReceiver receiver,String request)
    {
        Intent service = new Intent(ctx, BacEmfFindService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", request);
        service.putExtra("dtoTag", dto);
        service.putExtra("receiverTag", receiver);

        ctx.startService(service);
    }
    private void cudService(BackEmfDTO dto, ServiceResultReceiver receiver,String request)
    {
        Intent service = new Intent(ctx, BackEmfCUDService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", request);
        service.putExtra("dtoTag", dto);
        service.putExtra("receiverTag", receiver);

        ctx.startService(service);
    }

    public void findAllService( BackEmfDTO dto, ServiceResultReceiver receiver)
    {
        findService(dto, receiver, "find all");
    }

    public void findByIdService( BackEmfDTO dto, ServiceResultReceiver receiver)
    {
        findService(dto, receiver, "find by id");
    }

    public void createService(BackEmfDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto,receiver,"create");
    }

    public void DeleteService(BackEmfDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "delete");
    }
    public void updateService(BackEmfDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "update");
    }
    public void dropTableService(BackEmfDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "drop");
    }
}

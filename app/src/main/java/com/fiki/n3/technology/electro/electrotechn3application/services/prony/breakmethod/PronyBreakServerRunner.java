package com.fiki.n3.technology.electro.electrotechn3application.services.prony.breakmethod;

import android.content.Context;
import android.content.Intent;

import com.fiki.n3.technology.electro.electrotechn3application.dto.PronyBreakDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;

/**
 * Created by Roman on 2016/05/26.
 */
public class PronyBreakServerRunner {
    private Context ctx;

    public PronyBreakServerRunner(Context ctx) {
        this.ctx = ctx;
    }

    private void findService(PronyBreakDTO dto, ServiceResultReceiver receiver, String request)
    {
        Intent service = new Intent(ctx, PronyBreakFindService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", request);
        service.putExtra("dtoTag", dto);
        service.putExtra("receiverTag", receiver);

        ctx.startService(service);
    }
    private void cudService(PronyBreakDTO dto, ServiceResultReceiver receiver,String request)
    {
        Intent service = new Intent(ctx, PronyBreakCUDService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", request);
        service.putExtra("dtoTag", dto);
        service.putExtra("receiverTag", receiver);

        ctx.startService(service);
    }

    public void findAllService( PronyBreakDTO dto, ServiceResultReceiver receiver)
    {
        findService(dto, receiver, "find all");
    }

    public void findByIdService( PronyBreakDTO dto, ServiceResultReceiver receiver)
    {
        findService(dto, receiver, "find by id");
    }

    public void createService(PronyBreakDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto,receiver,"create");
    }

    public void DeleteService(PronyBreakDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "delete");
    }
    public void updateService(PronyBreakDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "update");
    }
    public void dropTableService(PronyBreakDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "drop");
    }

}

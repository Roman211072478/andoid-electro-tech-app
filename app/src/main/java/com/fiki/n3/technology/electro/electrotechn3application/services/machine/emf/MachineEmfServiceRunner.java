package com.fiki.n3.technology.electro.electrotechn3application.services.machine.emf;

import android.content.Context;
import android.content.Intent;

import com.fiki.roman.andoirdmathlab.dto.MachineEmfDTO;
import com.fiki.roman.andoirdmathlab.receiver.ServiceResultReceiver;

/**
 * Created by Roman on 2016/05/26.
 */
public class MachineEmfServiceRunner {
    private Context ctx;

    public MachineEmfServiceRunner(Context ctx) {
        this.ctx = ctx;
    }

    private void findService(MachineEmfDTO dto, ServiceResultReceiver receiver,String request)
    {
        Intent service = new Intent(ctx, MachineEmfFindService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", request);
        service.putExtra("dtoTag", dto);
        service.putExtra("receiverTag", receiver);

        ctx.startService(service);
    }
    private void cudService(MachineEmfDTO dto, ServiceResultReceiver receiver,String request)
    {
        Intent service = new Intent(ctx, MachineEmfCUDService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", request);
        service.putExtra("dtoTag", dto);
        service.putExtra("receiverTag", receiver);

        ctx.startService(service);
    }

    public void findAllService( MachineEmfDTO dto, ServiceResultReceiver receiver)
    {
        findService(dto, receiver, "find all");
    }

    public void findByIdService( MachineEmfDTO dto, ServiceResultReceiver receiver)
    {
        findService(dto, receiver, "find by id");
    }

    public void createService(MachineEmfDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto,receiver,"create");
    }

    public void DeleteService(MachineEmfDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "delete");
    }
    public void updateService(MachineEmfDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "update");
    }
    public void dropTableService(MachineEmfDTO dto, ServiceResultReceiver receiver)
    {
        cudService(dto, receiver, "drop");
    }

}

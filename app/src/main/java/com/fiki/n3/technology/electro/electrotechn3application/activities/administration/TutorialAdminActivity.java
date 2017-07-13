package com.fiki.n3.technology.electro.electrotechn3application.activities.administration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.activities.administration.back.emf.BackEmfCrudMenuAcitivity;
import com.fiki.n3.technology.electro.electrotechn3application.activities.administration.machine.emf.MachineEmfCrud;
import com.fiki.n3.technology.electro.electrotechn3application.activities.administration.prony.PronyBrakeCRUDActivity;
import com.fiki.n3.technology.electro.electrotechn3application.activities.administration.rope.brake.RopeBrakeCrudActivity;
import com.fiki.n3.technology.electro.electrotechn3application.activities.administration.torque.TorqueCrudActivity;


/**
 * Created by Roman on 2016/05/16.
 */
public class TutorialAdminActivity extends Activity {

    private Button backEmfBtn,machineEmfBtn,torqueBtn,ropeBtn,pronyBtn,swine_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crud_tutorials);

        //initiate components

        initComponents();
        setButtons();
    }

    private void setButtons() {
        backeEmf();
        machineEmf();
        torque();
        rope();
        prony();
        swine();
    }

    private void swine() {
      //  swine_btn
    }

    private void prony() {
        pronyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), PronyBrakeCRUDActivity.class);
            startActivity(intent);
            }
        });
      //  pronyBtn
    }

    private void rope() {
        ropeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RopeBrakeCrudActivity.class);
                startActivity(intent);
            }
        });
    }

    private void torque() {
        torqueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TutorialAdminActivity.this,TorqueCrudActivity.class);
                startActivity(intent);
            }
        });

    }

    private void machineEmf() {
        machineEmfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TutorialAdminActivity.this,MachineEmfCrud.class);
                startActivity(intent);
            }
        });

      //  machineEmfBtn
    }

    private void backeEmf() {
        backEmfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TutorialAdminActivity.this,BackEmfCrudMenuAcitivity.class);
                startActivity(intent);

            }
        });
    }

    private void initComponents() {
        backEmfBtn = (Button)findViewById(R.id.back_emf_admin_crud);
        machineEmfBtn = (Button)findViewById(R.id.machine_emf__admin_crud);
        torqueBtn = (Button)findViewById(R.id.torque_admin_crud);
        ropeBtn = (Button)findViewById(R.id.rope__admin_crud);
        pronyBtn = (Button)findViewById(R.id.prony__admin_crud);
        swine_btn =(Button)findViewById(R.id.swine__admin_crud);

    }
}

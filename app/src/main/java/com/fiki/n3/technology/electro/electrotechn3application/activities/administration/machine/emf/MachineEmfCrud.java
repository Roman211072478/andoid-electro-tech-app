package com.fiki.n3.technology.electro.electrotechn3application.activities.administration.machine.emf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fiki.n3.technology.electro.electrotechn3application.R;


public class MachineEmfCrud extends Activity {

    private Button createBtn,editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_emf_crud);

        //initiate components
        initiateComponents();

        initButtons();
    }
    private void initButtons() {
        createButton();

        editButton();

    }

    private void editButton() {
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MachineEmfCrud.this, MachineEmfEditActivity.class);
                startActivity(intent);

            }
        });
    }

    private void createButton() {
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MachineEmfCrud.this, MachineEmfCreateActivity.class);
                startActivity(intent);

            }
        });
    }

    private void initiateComponents() {
        createBtn = (Button)findViewById(R.id.machine_emf_create_crud_lay_);
        editBtn = (Button)findViewById(R.id.machine_emf_edit_crud_lay_);
    }
}

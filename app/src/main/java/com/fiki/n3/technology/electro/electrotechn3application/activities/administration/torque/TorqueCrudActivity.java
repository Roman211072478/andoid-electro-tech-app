package com.fiki.n3.technology.electro.electrotechn3application.activities.administration.torque;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fiki.n3.technology.electro.electrotechn3application.R;


public class TorqueCrudActivity extends Activity {

    private Button createBtn,editBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.torque_crud_activity);//initiate components
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
                Intent intent = new Intent(TorqueCrudActivity.this, TorqueEditActivity.class);
                startActivity(intent);

            }
        });
    }

    private void createButton() {
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TorqueCrudActivity.this, TorqueCreateActivity.class);
                startActivity(intent);

            }
        });
    }

    private void initiateComponents() {
        createBtn = (Button)findViewById(R.id.create_torque_crud_menu_activity);
        editBtn = (Button)findViewById(R.id.edit_torque_crud_menu_activity);
    }

}

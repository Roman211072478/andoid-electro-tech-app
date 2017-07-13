package com.fiki.n3.technology.electro.electrotechn3application.activities.administration.prony;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.fiki.n3.technology.electro.electrotechn3application.R;


public class PronyBrakeCRUDActivity extends AppCompatActivity {
    private Button createBtn,editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prony_brake_crud_activity);

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
                Intent intent = new Intent(getApplicationContext(), PronyBrakeEditActivity.class);
                startActivity(intent);

            }
        });
    }

    private void createButton() {
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PronyBrakeCreateActivity.class);
                startActivity(intent);

            }
        });
    }

    private void initiateComponents() {
        createBtn = (Button)findViewById(R.id.create_prony_brake_crud_menu_activity);
        editBtn = (Button)findViewById(R.id.edit_prony_brake_crud_menu_activity);
    }
}

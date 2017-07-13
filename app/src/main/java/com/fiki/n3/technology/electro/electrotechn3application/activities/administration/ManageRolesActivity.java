package com.fiki.n3.technology.electro.electrotechn3application.activities.administration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fiki.n3.technology.electro.electrotechn3application.R;


/**
 * Created by Roman on 2016/05/12.
 */
public class ManageRolesActivity extends Activity {

    private Button btnCreate,btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_roles);

        btnCreate = (Button)findViewById(R.id.manange_roles_create);
        btnEdit = (Button)findViewById(R.id.manange_roles_up_del);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ManageRolesActivity.this,CreateRoleActivity.class);
                startActivity(intent);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ManageRolesActivity.this,EditRoleActivity.class);
                startActivity(intent);
            }
        });

    }



    }

package com.fiki.n3.technology.electro.electrotechn3application.activities.administration;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fiki.math.lab.mathlab.domain.model.Role;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.RoleDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.services.role.RoleCUDService;

/**
 * Created by Roman on 2016/05/12.
 */
public class CreateRoleActivity extends Activity implements ServiceResultReceiver.Receiver{

    private Button btnSubmit,btnClear;
    private EditText txtRole,txtDescription;
    public ServiceResultReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.role_create);

        receiver = new ServiceResultReceiver(new Handler());

        receiver.setReceiver(this);

        initButtons();
        setButtons();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

        ResultDTO resultDTOFound = null;
        resultDTOFound = (ResultDTO)resultData.getSerializable("ServiceTag");


        if (resultDTOFound == null) {


        }
        else{
            if(resultDTOFound.getSResult().equals("-1"))
            {
                Dialog d = new Dialog(CreateRoleActivity.this);
                d.setTitle("Create error");
                TextView tv = new TextView(CreateRoleActivity.this);

                tv.setText("insert was unsuccessful");
                d.setContentView(tv);
                d.show();
            }
            else
            {
                Dialog d = new Dialog(CreateRoleActivity.this);
                d.setTitle("Create role");
                TextView tv = new TextView(CreateRoleActivity.this);

                tv.setText("successful");
                d.setContentView(tv);
                d.show();

                txtRole.setText("");
                txtDescription.setText("");
            }
        }


    }

    private void setButtons()
    {
        createButton();
        clearButton();

    }

    private void createButton()
    {

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtRole.getText().toString().equals("") || txtDescription.getText().toString().equals(""))
                {
                    Dialog d = new Dialog(CreateRoleActivity.this);
                    d.setTitle("Create");
                    TextView tv = new TextView(CreateRoleActivity.this);

                    tv.setText("Please insert values");
                    d.setContentView(tv);
                    d.show();

                }
                else
                {
                    String role = txtRole.getText().toString();
                    String description = txtDescription.getText().toString();

                    Role roleObject = new Role();
                    roleObject.setRole(role);
                    roleObject.setDescription(description);

                    RoleDTO dto = new RoleDTO.Builder()
                            .role(roleObject)
                            .build();

                    try {
                        Intent service;
                        service = new Intent();
                        service.setClass(CreateRoleActivity.this, RoleCUDService.class);

                        //put extra values into the intent, to be send to the called service
                        service.putExtra("requestTag", "create");
                        service.putExtra("dtoTag", dto);
                        service.putExtra("receiverTag", receiver);

                        startService(service);
                    } catch (Exception e) {
                        Dialog d = new Dialog(CreateRoleActivity.this);
                        d.setTitle("Error");
                        TextView tv = new TextView(CreateRoleActivity.this);

                        tv.setText(e.getMessage());
                        d.setContentView(tv);
                        d.show();
                    }
                }
            }
        });




    }
    private void clearButton()
    {
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtRole.setText("");
                txtDescription.setText("");
            }
        });
    }


        private void initButtons() {
            btnSubmit = (Button) findViewById(R.id.create_role_create_btn);

            btnClear = (Button) findViewById(R.id.create_role_cleatbtn);

            txtRole = (EditText) findViewById(R.id.create_role_roleTxt);
            txtDescription = (EditText) findViewById(R.id.create_role_description);
        }
    }

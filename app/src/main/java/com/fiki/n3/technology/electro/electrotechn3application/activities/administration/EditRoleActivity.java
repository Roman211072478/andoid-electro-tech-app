package com.fiki.n3.technology.electro.electrotechn3application.activities.administration;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fiki.math.lab.mathlab.domain.model.Role;
import com.fiki.n3.technology.electro.electrotechn3application.R;
import com.fiki.n3.technology.electro.electrotechn3application.dto.ResultDTO;
import com.fiki.n3.technology.electro.electrotechn3application.dto.RoleDTO;
import com.fiki.n3.technology.electro.electrotechn3application.receiver.ServiceResultReceiver;
import com.fiki.n3.technology.electro.electrotechn3application.services.role.RoleCUDService;
import com.fiki.n3.technology.electro.electrotechn3application.services.role.RoleFindService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Roman on 2016/05/12.
 */
public class EditRoleActivity extends Activity implements ServiceResultReceiver.Receiver {

    private Button btnUpdate,btnClear,btnDelete;
    private EditText txtRole,txtDescription,txtRoleID;
    public ServiceResultReceiver receiver;
    private ListView lvRoles;
    private List<Role> roleList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.role_edit_layout);

        receiver = new ServiceResultReceiver(new Handler());

        receiver.setReceiver(this);


        initButtons();

        setButtons();
       populateService();

    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

        ResultDTO resultDTOFound = null;
        resultDTOFound = (ResultDTO)resultData.getSerializable("ServiceTag");
        //Geting the listView

        if(resultDTOFound.getRequest().equals("find all"))
        {
            roleList = listPopulateHander(resultDTOFound);
        }
        else if(resultDTOFound.getRequest().equals("delete"))
        {
                if(resultDTOFound.getSResult().equals("-1"))
                {
                    Dialog d = new Dialog(EditRoleActivity.this);
                    d.setTitle("delete");
                    TextView tv = new TextView(EditRoleActivity.this);

                    tv.setText("Delete was unsuccessful");
                    d.setContentView(tv);
                    d.show();
                }
                else
                {
                    Toast.makeText(EditRoleActivity.this,"success",Toast.LENGTH_SHORT).show();
                    clearFields();
                    populateService();//reload list view
                }
        }
        else if(resultDTOFound.getRequest().equals("update"))
        {
            if(resultDTOFound.getSResult().equals("-1"))
            {
                Dialog d = new Dialog(EditRoleActivity.this);
                d.setTitle("update");
                TextView tv = new TextView(EditRoleActivity.this);

                tv.setText("update was unsuccessful");
                d.setContentView(tv);
                d.show();
            }
            else
            {
                Toast.makeText(EditRoleActivity.this,"success",Toast.LENGTH_SHORT).show();
                clearFields();
                populateService();//reload list view
            }
        }




    }

    private List<Role> listPopulateHander(ResultDTO resultDTO)
    {
        lvRoles.setAdapter(null);
        if(resultDTO.getSResult().equals("-1"))
        {
            lvRoles.setAdapter(null);
        }
        else {
            List<String> list = new ArrayList<String>();

            List<Role> roles = new ArrayList<Role>();

            HashMap myList = new HashMap();
            HashMap objectMap = new HashMap();
            objectMap = resultDTO.getResult();


            myList = (HashMap) objectMap.get("result");


            //Object
            Role role;

            if (!myList.isEmpty()) {
                // Get a set of the entries
                Set set = myList.entrySet();

                // Get an iterator
                Iterator i = set.iterator();

                list.clear();//Clear the list

                while (i.hasNext()) {
                    Map.Entry me = (Map.Entry) i.next();

                    role = (Role) me.getValue();

                    roles.add(role);
                    list.add(role.getRoleId() + " | " + role.getRole() + " | " + role.getDescription());//Add to string list
                }


                //Set Values to listview
                lvRoles.setAdapter(new ArrayAdapter<String>(EditRoleActivity.this, android.R.layout.simple_list_item_1, list));
                return roles;
            }
        }

        return null;
    }

    private void setOnItemClick()
    {
        lvRoles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String data = (String) parent.getItemAtPosition(position);


                if (!(data.trim()).equals("")) {

                    txtRole.setText(roleList.get(position).getRole().toString());
                    txtDescription.setText(roleList.get(position).getDescription().toString());
                    txtRoleID.setText(roleList.get(position).getRoleId().toString());
                }
            }
        });
    }





    private void setButtons()
    {
        deleteButton();
        clearButton();
        setOnItemClick();
        update();


    }

    private void populateService()
    {
        RoleDTO roleDTO = new RoleDTO.Builder()
                .build();

        Intent service;
        service = new Intent(EditRoleActivity.this,RoleFindService.class);

        service.putExtra("requestTag", "find all");
        service.putExtra("dtoTag", roleDTO);
        service.putExtra("receiverTag", receiver);

        startService(service);
    }

    private void update()
    {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtRole.getText().toString().equals("") || txtDescription.getText().toString().equals("")) {
                    Dialog d = new Dialog(EditRoleActivity.this);
                    d.setTitle("edit");
                    TextView tv = new TextView(EditRoleActivity.this);

                    tv.setText("Please insert values");
                    d.setContentView(tv);
                    d.show();

                } else {

                    String role = txtRole.getText().toString();
                    String description = txtDescription.getText().toString();
                    Integer role_id = new Integer(txtRoleID.getText().toString());

                    Role roleObject = new Role();
                    roleObject.setRole(role);
                    roleObject.setRoleId(role_id);
                    roleObject.setDescription(description);

                    RoleDTO dto = new RoleDTO.Builder()
                            .role(roleObject)
                            .roleId(role_id)
                            .build();

                    try {

                        getCUDService("update", dto);

                    } catch (Exception e) {
                        Dialog d = new Dialog(EditRoleActivity.this);
                        d.setTitle("Error");
                        TextView tv = new TextView(EditRoleActivity.this);

                        tv.setText(e.getMessage());
                        d.setContentView(tv);
                        d.show();
                    }
                }
            }
        });
    }
    private void deleteButton()
    {

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtRole.getText().toString().equals("") || txtDescription.getText().toString().equals("")) {
                    Dialog d = new Dialog(EditRoleActivity.this);
                    d.setTitle("edit");
                    TextView tv = new TextView(EditRoleActivity.this);

                    tv.setText("Please insert values");
                    d.setContentView(tv);
                    d.show();

                } else {

                    String role = txtRole.getText().toString();
                    String description = txtDescription.getText().toString();
                    Integer role_id = new Integer(txtRoleID.getText().toString());

                    Role roleObject = new Role();
                    roleObject.setRole(role);
                    roleObject.setRoleId(role_id);
                    roleObject.setDescription(description);

                    RoleDTO dto = new RoleDTO.Builder()
                            .role(roleObject)
                            .roleId(role_id)
                            .build();

                    try {

                        getCUDService("delete", dto);

                    } catch (Exception e) {
                        Dialog d = new Dialog(EditRoleActivity.this);
                        d.setTitle("Error");
                        TextView tv = new TextView(EditRoleActivity.this);

                        tv.setText(e.getMessage());
                        d.setContentView(tv);
                        d.show();
                    }
                }
            }
        });

    }

    private void getCUDService(String sRequest, RoleDTO objectDTO)
    {
        //initialize intent and run SERVICE
        Intent service = new Intent(EditRoleActivity.this, RoleCUDService.class);

        //put extra values into the intent, to be send to the called service
        service.putExtra("requestTag", sRequest);
        service.putExtra("dtoTag", objectDTO);
        service.putExtra("receiverTag", receiver);

        startService(service);

    }
    private void clearButton()
    {
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });
    }

    private void clearFields()
    {
        txtRole.setText("");
        txtDescription.setText("");
        txtRoleID.setText("");
    }


    private void initButtons() {
        btnDelete = (Button) findViewById(R.id.role_edit_delete);
        btnUpdate = (Button) findViewById(R.id.role_edit_update);

        btnClear = (Button) findViewById(R.id.role_edit_clear);
        lvRoles = (ListView)findViewById(R.id.role_list_view);

        txtRole = (EditText) findViewById(R.id.role_edit_role);
        txtDescription = (EditText) findViewById(R.id.role_edit_description);
        txtRoleID = (EditText) findViewById(R.id.role_edit_roleId);
    }
}


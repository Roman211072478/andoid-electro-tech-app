package com.fiki.n3.technology.electro.electrotechn3application.activities.administration.back.emf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fiki.n3.technology.electro.electrotechn3application.R;
/**
 * Created by Roman on 2016/05/16.
 */
public class BackEmfCrudMenuAcitivity extends Activity {

    private Button createBtn,editBtn;

    public BackEmfCrudMenuAcitivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.back_emf_crud);



        //initiate components
        initiateComponents();

        inintButtons();

    }

    private void inintButtons() {
        createButton();

        editButton();

    }

    private void editButton() {
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BackEmfCrudMenuAcitivity.this, BackEmfEditActivity.class);
                startActivity(intent);

            }
        });
    }

    private void createButton() {
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BackEmfCrudMenuAcitivity.this, BackEmfCreateActivity.class);
                startActivity(intent);

            }
        });
    }

    private void initiateComponents() {
        createBtn = (Button)findViewById(R.id.create_back_emf_crud_menu_activity);
        editBtn = (Button)findViewById(R.id.edit_back_emf_crud_menu_activity);
    }


}

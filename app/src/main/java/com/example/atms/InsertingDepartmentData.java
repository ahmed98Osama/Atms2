package com.example.atms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertingDepartmentData extends AppCompatActivity {


    Button button;
    EditText departmentNameAdded,departmentHead;
    FirebaseDatabase database;
    DatabaseReference departmentRef;
    DepartmentDataModel departmentDataModel;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserting_department_data);

        database=FirebaseDatabase.getInstance();
        departmentRef = database.getReference("departments");

        button = findViewById(R.id.save_department_data);

        back = findViewById(R.id.go_back);

        departmentNameAdded = findViewById(R.id.department_name_added_by_admin);
        departmentHead= findViewById(R.id.department_head);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = departmentNameAdded.getText().toString();
                String head = departmentHead.getText().toString();
                if (TextUtils.isEmpty(name)== true && TextUtils.isEmpty(head)== true ){
                    Toast.makeText(InsertingDepartmentData.this, "please insert all data", Toast.LENGTH_SHORT).show();
                }else{

                    //departmentDataModel = new DepartmentDataModel(name, head);
                    String idRef =  departmentRef.push().getKey();
                    departmentRef.child(idRef).child("id").setValue(idRef);
                    departmentRef.child(idRef).child("departmentName").setValue(name);
                    departmentRef.child(idRef).child("departmentHead").setValue(head);

                    departmentHead.getText().clear();
                    departmentNameAdded.getText().clear();

                    finish();
                }



            }
        });




    }
}
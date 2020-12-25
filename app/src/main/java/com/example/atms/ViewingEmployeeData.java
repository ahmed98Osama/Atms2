package com.example.atms;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewingEmployeeData extends AppCompatActivity {

    TextView employeeName,employeeEmail, employeeDepartment,employeePhoneNumber;
    DatabaseReference getEmployeeDataRef ;
    DatabaseReference departmentRef;
    ImageView employeeProfileImage;
    ImageView back;
    Button editEmployeeData,saveEmployeeData;
    Spinner listView;

    ArrayList<String> spinnerList;
    ArrayAdapter<String> spinnerAdapter;

    String name,ic;
    String depName;


    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing_employee_data);

        getEmployeeDataRef = FirebaseDatabase.getInstance().getReference("Users");
        departmentRef = FirebaseDatabase.getInstance().getReference("departments");

        employeeProfileImage = findViewById(R.id.employee_profile_image);
        employeeName = findViewById(R.id.employee_name_added_by_admin);
        employeeEmail = findViewById(R.id.employee_email);
        employeeDepartment = findViewById(R.id.department_of_the_employee);
        employeePhoneNumber = findViewById(R.id.phone_number_employee);
        back = findViewById(R.id.go_back);
        editEmployeeData = findViewById(R.id.edit_employee_data);
        saveEmployeeData = findViewById(R.id.save_employee_data);

        listView = findViewById(R.id.set_department_for_employee);

        spinnerList = new ArrayList<>();
        spinnerAdapter = new ArrayAdapter<>(ViewingEmployeeData.this,R.layout.department_item_swipe,R.id.dep_name,spinnerList);
        listView.setAdapter(spinnerAdapter);

        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               depName = spinnerList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        button = findViewById(R.id.view_employee_permissions);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        editEmployeeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setVisibility(View.VISIBLE);
                saveEmployeeData.setVisibility(View.VISIBLE);
            }
        });

        saveEmployeeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEmployeeDataRef.child(ic).child("department").setValue(depName);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        name = getIntent().getStringExtra("employeeName");
         ic = getIntent().getStringExtra("employeeId");



        getDepartment();
        getEmployeeData();
    }

    public void getEmployeeData() {

        getEmployeeDataRef.child(ic).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    EmployeeDataModel employeeDataModel = snapshot.getValue(EmployeeDataModel.class);
                    String imageLink = employeeDataModel.getUri();
                    Picasso.get().load(imageLink).into(employeeProfileImage);
                    employeeName.setText(employeeDataModel.getName());
                    employeeEmail.setText(employeeDataModel.getEmail());
                    if ( employeeDataModel.getDepartment()== null){
                        Toast.makeText(ViewingEmployeeData.this, "this person is not signed for a department ", Toast.LENGTH_SHORT).show();
                    }else{
                        employeeDepartment.setText(employeeDataModel.getDepartment());
                    }

                    employeePhoneNumber.setText(employeeDataModel.getPhone());


                } else {
                    Toast.makeText(ViewingEmployeeData.this, "error", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewingEmployeeData.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void getDepartment(){

        departmentRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                DepartmentDataModel model = snapshot.getValue(DepartmentDataModel.class);
                spinnerList.add(model.getDepartmentName());
                spinnerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}

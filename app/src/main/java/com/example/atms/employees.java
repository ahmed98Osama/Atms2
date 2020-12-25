package com.example.atms;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.example.atms.employees#newInstance} factory method to
 * create an instance of this fragment.
 */
public class employees extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public employees() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment employees.
     */
    // TODO: Rename and change types and number of parameters
    public static com.example.atms.employees newInstance(String param1, String param2) {
        com.example.atms.employees fragment = new com.example.atms.employees();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    
    ArrayAdapter<String> employeesListAdapter;
    ArrayList<String> employeesArrayList;
    ListView employeesList;
    ArrayList<String> employeeIdList;
    DatabaseReference employeeRef;
    FirebaseDatabase database;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V= inflater.inflate(R.layout.fragment_employees, container, false);



        database = FirebaseDatabase.getInstance();
        employeeRef = database.getReference().child("Users");


        employeesList = V.findViewById(R.id.employees_list);
        employeesArrayList = new ArrayList<String>();
        employeeIdList = new ArrayList<String>();
        employeesListAdapter = new ArrayAdapter<String>(getContext(),R.layout.emp_item,R.id.emp_name,employeesArrayList);


        employeesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getContext(), ViewingEmployeeData.class);
               intent.putExtra("employeeName",employeesArrayList.get(position));
               intent.putExtra("employeeId",employeeIdList.get(position));
               startActivity(intent);


            }
        });


        getEmployeesName();
        return V;
    }

    public void getEmployeesName(){

        employeesList.setAdapter(employeesListAdapter);

        employeeRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                EmployeeDataModel employeeDataModel = snapshot.getValue(EmployeeDataModel.class);
                employeesArrayList.add(employeeDataModel.getName());
                employeeIdList.add(employeeDataModel.getId());

                employeesListAdapter.notifyDataSetChanged();

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
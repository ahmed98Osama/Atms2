package com.example.atms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link com.example.atms.departments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class departments extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public departments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment departments.
     */
    // TODO: Rename and change types and number of parameters
    public static com.example.atms.departments newInstance(String param1, String param2) {
        com.example.atms.departments fragment = new com.example.atms.departments();
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


     ListView departmentsList;
    ArrayAdapter<String> departmentListAdapter;
    ArrayList<String> departmentArrayList;
    ImageButton addDepartment;

    FirebaseDatabase database;
    DatabaseReference departmentRef;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      final View V=  inflater.inflate(R.layout.fragment_departments, container, false);

      database=FirebaseDatabase.getInstance();
      departmentRef = database.getReference().child("departments");

      addDepartment = V.findViewById(R.id.add_department);
      departmentsList = V.findViewById(R.id.dep_list);


      departmentArrayList = new ArrayList<String>();

      departmentListAdapter = new ArrayAdapter<String>(getContext(),R.layout.department_item_swipe,R.id.dep_name,departmentArrayList);

        addDepartment.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              Intent intent = new Intent(getContext(),InsertingDepartmentData.class);
              startActivity(intent);

          }
      });



        delete();
        getData();
        return V;
    }

   public void getData(){

       departmentsList.setAdapter(departmentListAdapter);

       departmentRef.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

               DepartmentDataModel departmentDataModel = snapshot.getValue(DepartmentDataModel.class);
               departmentArrayList.add(departmentDataModel.getDepartmentName());
               departmentListAdapter.notifyDataSetChanged();

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



   public void delete(){
       departmentsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

               final int which_item = position;

               new AlertDialog.Builder(getContext())
                       .setIcon(R.drawable.ic_delete)
                       .setTitle("Are you sure ?")
                       .setTitle("Do you want to delete this item ")
                       .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               String name = departmentArrayList.get(position);
                               departmentArrayList.remove(which_item);
                               departmentListAdapter.notifyDataSetChanged();


                               Query query = departmentRef.orderByChild("departmentName").equalTo(name);

                               query.addListenerForSingleValueEvent(new ValueEventListener() {
                                   @Override
                                   public void onDataChange(@NonNull DataSnapshot snapshot) {


                                       for (DataSnapshot child: snapshot.getChildren()) {
                                           Log.d("User key", child.getKey());
                                           Log.d("User ref", child.getRef().toString());
                                           Log.d("User val", child.getValue().toString());
                                       }
                                   }

                                   @Override
                                   public void onCancelled(@NonNull DatabaseError error) {

                                   }
                               });
                           }
                       })
                       .setNegativeButton("No",null)
                       .show();
               return true;
           }
       });
   }

}







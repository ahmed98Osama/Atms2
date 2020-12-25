package com.example.atms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class Available extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    RecyclerView recView;
    AvailbleAdapter adapter;
    public Available() {
        // Required empty public constructor
    }


    public static Available newInstance(String param1, String param2) {
        Available fragment = new Available();
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

    DatabaseReference availableRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_available, container, false);

        availableRef = FirebaseDatabase.getInstance().getReference().child("Users");

        recView = v.findViewById(R.id.available_recview);
        recView.setLayoutManager(new LinearLayoutManager(getContext()));


        Query query = availableRef.orderByChild("available").equalTo("yes");
        FirebaseRecyclerOptions<EmployeeDataModel> options =
                new FirebaseRecyclerOptions.Builder<EmployeeDataModel>()
                .setQuery(query,EmployeeDataModel.class)
                .build();

        adapter = new AvailbleAdapter(options);
        recView.setAdapter(adapter);


        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
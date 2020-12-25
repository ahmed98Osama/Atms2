package com.example.atms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class requests extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    RecyclerView recView;
    requestAdapter adapter;
    public requests() {
        // Required empty public constructor
    }

    public static com.example.atms.requests newInstance(String param1, String param2) {
        com.example.atms.requests fragment = new com.example.atms.requests();
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

    DatabaseReference requestRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_requests, container, false);

        requestRef = FirebaseDatabase.getInstance().getReference().child("Requests");

        recView = v.findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(getContext()));

        Query query = requestRef.orderByChild("status").equalTo("0");
        FirebaseRecyclerOptions<requestDataModel> options =
                new FirebaseRecyclerOptions.Builder<requestDataModel>()
                    .setQuery(query,requestDataModel.class)
                    .build();

        adapter = new requestAdapter(options);
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
package com.example.atms;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class requestAdapter extends FirebaseRecyclerAdapter<requestDataModel,requestAdapter.myViewHolder> {

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Requests");

    public requestAdapter(@NonNull FirebaseRecyclerOptions<requestDataModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull requestDataModel model) {

        holder.name.setText(model.getName());
        holder.message.setText(model.getMessage());
        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("message",model.getId());
                String id = model.getId();
                reference.child(id).child("status").setValue("1");

            }
        });

        holder.rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = model.getId();
                reference.child(id).child("status").setValue("2");
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item,parent,false);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView name, message;
        Button acceptButton, rejectButton;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.employee_name_request);
            message = itemView.findViewById(R.id.request_message);
            acceptButton = itemView.findViewById(R.id.accept_request);
            rejectButton = itemView.findViewById(R.id.reject_request);


        }
    }

}

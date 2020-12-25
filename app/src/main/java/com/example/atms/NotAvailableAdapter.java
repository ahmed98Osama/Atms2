package com.example.atms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class NotAvailableAdapter extends FirebaseRecyclerAdapter<EmployeeDataModel,NotAvailableAdapter.myViewHolder> {

    public NotAvailableAdapter(@NonNull FirebaseRecyclerOptions<EmployeeDataModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull EmployeeDataModel model) {

        holder.name.setText(model.getName());
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.availble_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.employee_name_available);

        }
    }
}

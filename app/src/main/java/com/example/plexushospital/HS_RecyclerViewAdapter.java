package com.example.plexushospital;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HS_RecyclerViewAdapter extends RecyclerView.Adapter<HS_RecyclerViewAdapter.MyHospitalViewHolder> {
    Context context ;
    ArrayList<ServicesModel>servicesModels ;

    public HS_RecyclerViewAdapter(Context context, ArrayList<ServicesModel> servicesModels) {
        this.context = context;
        this.servicesModels = servicesModels;
    }

    @NonNull
    @Override
    public HS_RecyclerViewAdapter.MyHospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context) ;
        View view = layoutInflater.inflate(R.layout.recycler_view_row , parent , false);
        return new HS_RecyclerViewAdapter.MyHospitalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HS_RecyclerViewAdapter.MyHospitalViewHolder holder, int position) {
        holder.tvServicesName.setText(servicesModels.get(position).getName());
        holder.imageServices.setImageResource(servicesModels.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return servicesModels.size();
    }
    public static class MyHospitalViewHolder extends RecyclerView.ViewHolder{

        ImageView imageServices;
        TextView tvServicesName ;

        public MyHospitalViewHolder(@NonNull View itemView) {
            super(itemView);
            imageServices = itemView.findViewById(R.id.serviceImageView);
            tvServicesName = itemView.findViewById(R.id.serviceTextView);
        }
    }
}

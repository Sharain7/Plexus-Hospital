//package com.example.plexushospital;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class AS_RecyclerViewAdapter extends RecyclerView.Adapter<AS_RecyclerViewAdapter.MyAppointmentViewHolder>{
//    Context context ;
//    ArrayList<AppointmentModel>appointmentModels ;
//    ArrayList<String> doctorNames;
//
//    public AS_RecyclerViewAdapter(Context context , ArrayList<AppointmentModel>appointmentModels , ArrayList<String>doctorNames ) {
//        this.context  = context ;
//        this.appointmentModels = appointmentModels ;
//        this.doctorNames = doctorNames ;
//
//    }
//
//    @NonNull
//    @Override
//    public AS_RecyclerViewAdapter.MyAppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View view  = layoutInflater.inflate(R.layout.recycler_view_appointment_row , parent , false);
//        return new AS_RecyclerViewAdapter.MyAppointmentViewHolder(view);
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull AS_RecyclerViewAdapter.MyAppointmentViewHolder holder, int position) {
//        holder.tvAppointment.setText(appointmentModels.get(position).getName());
//        if (appointmentModels.get(position).isHasDropdown()) {
//            // Handle dropdown logic, if needed
//            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, doctorNames);
//            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            holder.dropdownSpinner.setAdapter(spinnerAdapter);
//            holder.dropdownSpinner.setVisibility(View.VISIBLE);
//
//        }
//        else
//        {
//            holder.dropdownSpinner.setVisibility(View.GONE);
//        }
//
//        // Check if the current item has a text area
//        if (appointmentModels.get(position).isHasTextArea()) {
//            holder.edtTextArea.setVisibility(View.VISIBLE);
//        } else {
//            holder.edtTextArea.setVisibility(View.GONE);
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return appointmentModels.size();
//    }
//    public static class MyAppointmentViewHolder extends RecyclerView.ViewHolder{
//
//        TextView tvAppointment ;
//        EditText edtTextArea ;
//        Spinner dropdownSpinner ;
//
//
//
//
//        public MyAppointmentViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tvAppointment = itemView.findViewById(R.id.tv_appointment);
//            edtTextArea = itemView.findViewById(R.id.editTextArea) ;
//            dropdownSpinner = itemView.findViewById(R.id.spinnerDropdown);
//
//
//        }
//
//    }
//}

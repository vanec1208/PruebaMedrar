package com.example.pruebamedrar.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pruebamedrar.R;
import com.example.pruebamedrar.beans.Pet;

import java.util.ArrayList;

public class PetSearchAdapter extends RecyclerView.Adapter<PetSearchAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Pet> listPets;
    private final OnFragmentListener mListener;

    public PetSearchAdapter(Context context, ArrayList<Pet> listPets, OnFragmentListener listener) {
        this.context = context;
        this.listPets = listPets;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_pet_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Pet pet = listPets.get(position);

        holder.lblId.setText(String.valueOf(pet.getId()));
        holder.lblName.setText(pet.getName());
        holder.lblType.setText(pet.getType());
        holder.lblAge.setText(String.valueOf(pet.getAge()));
        holder.lblBreed.setText(String.valueOf(pet.getBreed()));

        if(pet.getOwner() != null) {
            holder.lblOwnerId.setText(String.valueOf(pet.getOwner().getId()));
            holder.lblOwnerName.setText(pet.getOwner().getName());
            holder.lblOwnerPhone.setText(pet.getOwner().getPhone());
        }

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(context.getString(R.string.delete_confirmation));

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.selectPet(pet);
                        dialogInterface.dismiss();
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //public final View mView;

        public final TextView lblId;
        public final TextView lblName;
        public final TextView lblType;
        public final TextView lblAge;
        public final TextView lblBreed;
        public final TextView lblOwnerId;
        public final TextView lblOwnerName;
        public final TextView lblOwnerPhone;
        public final ImageButton btnDelete;

        public ViewHolder(View view) {
            super(view);
            //mView = view;

            lblId = view.findViewById(R.id.lblId);
            lblName = view.findViewById(R.id.lblName);
            lblType = view.findViewById(R.id.lblType);
            lblAge = view.findViewById(R.id.lblAge);
            lblBreed = view.findViewById(R.id.lblBreed);
            lblOwnerId = view.findViewById(R.id.lblOwnerId);
            lblOwnerName = view.findViewById(R.id.lblOwnerName);
            lblOwnerPhone = view.findViewById(R.id.lblOwnerPhone);
            btnDelete = view.findViewById(R.id.btnDelete);
        }
    }

    public void setList(ArrayList<Pet> listPets){
        this.listPets = listPets;
    }

    public interface OnFragmentListener {
        void selectPet(Pet pet);
    }
}

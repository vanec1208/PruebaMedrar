package com.example.pruebamedrar.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pruebamedrar.R;
import com.example.pruebamedrar.beans.PetVaccines;

import java.util.ArrayList;

public class VaccinesSearchAdapter extends RecyclerView.Adapter<VaccinesSearchAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PetVaccines> listVaccines;
    private final OnFragmentListener mListener;

    public VaccinesSearchAdapter(ArrayList<PetVaccines> listVaccines, OnFragmentListener listener) {
        this.listVaccines = listVaccines;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_vaccines_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final PetVaccines petVaccines = listVaccines.get(position);

        holder.lblId.setText(String.valueOf(petVaccines.getPetId()));
        holder.lblName.setText(petVaccines.getPetName());

        if(!TextUtils.isEmpty(petVaccines.getVaccines())) {
            holder.lblVaccines.setText(petVaccines.getVaccines());
        } else {
            holder.lblVaccines.setText(context.getString(R.string.vaccines_empty));
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.selectPet(petVaccines);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listVaccines.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final TextView lblId;
        public final TextView lblName;
        public final TextView lblVaccines;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            lblId = view.findViewById(R.id.lblId);
            lblName = view.findViewById(R.id.lblName);
            lblVaccines = view.findViewById(R.id.lblVaccines);
        }
    }

    public void setList(ArrayList<PetVaccines> listVaccines){
        this.listVaccines = listVaccines;
    }

    public interface OnFragmentListener {
        void selectPet(PetVaccines petVaccines);
    }
}

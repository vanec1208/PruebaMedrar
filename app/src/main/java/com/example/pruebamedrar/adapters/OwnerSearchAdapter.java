package com.example.pruebamedrar.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pruebamedrar.R;
import com.example.pruebamedrar.beans.Owner;

import java.util.ArrayList;

public class OwnerSearchAdapter extends RecyclerView.Adapter<OwnerSearchAdapter.ViewHolder> {
    private ArrayList<Owner> listOwners;
    private OnFragmentListener mListener;

    public OwnerSearchAdapter(OnFragmentListener mListener, ArrayList<Owner> listOwners) {
        this.mListener = mListener;
        this.listOwners = listOwners;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_owner_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Owner owner = listOwners.get(position);

        holder.lblId.setText(String.valueOf(owner.getId()));
        holder.lblName.setText(owner.getName());
        holder.lblPhone.setText(owner.getPhone());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null) {
                    mListener.selectOwner(owner);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOwners.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final TextView lblId;
        public final TextView lblName;
        public final TextView lblPhone;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            lblId = view.findViewById(R.id.lblId);
            lblName = view.findViewById(R.id.lblName);
            lblPhone = view.findViewById(R.id.lblPhone);
        }
    }

    public void setList(ArrayList<Owner> listOwners){
        this.listOwners = listOwners;
    }

    public interface OnFragmentListener {
        void selectOwner(Owner owner);
    }
}

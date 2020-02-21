package com.example.pruebamedrar.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.pruebamedrar.R;
import com.example.pruebamedrar.adapters.OwnerSearchAdapter;
import com.example.pruebamedrar.beans.Owner;
import com.example.pruebamedrar.fragments.PetRegistrationFragment;
import com.example.pruebamedrar.transactions.OwnerTransactions;

import java.util.ArrayList;

public class SearchDialog extends DialogFragment implements OwnerSearchAdapter.OnFragmentListener {

    private Context context;

    private EditText txtSearch;
    private RecyclerView recyclerView;
    private OwnerSearchAdapter ownerSearchAdapter;

    private ArrayList<Owner> listOwners = new ArrayList<>();

    public SearchDialog() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_dialog, container, false);

        txtSearch = view.findViewById(R.id.txtSearch);
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        ownerSearchAdapter = new OwnerSearchAdapter(this, listOwners);
        recyclerView.setAdapter(ownerSearchAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                listOwners = new ArrayList<>();
                String text = editable.toString();

                if (TextUtils.isEmpty(text)) {
                    ownerSearchAdapter.setList(listOwners);
                    ownerSearchAdapter.notifyDataSetChanged();
                } else {
                    listOwners = OwnerTransactions.getOwners(context, text);
                    ownerSearchAdapter.setList(listOwners);
                    ownerSearchAdapter.notifyDataSetChanged();
                }
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();

        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void selectOwner(Owner owner) {
        ((PetRegistrationFragment) getParentFragment()).selectOwner(owner);
    }
}

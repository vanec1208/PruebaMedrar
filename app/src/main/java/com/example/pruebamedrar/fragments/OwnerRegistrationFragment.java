package com.example.pruebamedrar.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pruebamedrar.R;
import com.example.pruebamedrar.beans.Owner;
import com.example.pruebamedrar.dialogs.AdviceDialog;
import com.example.pruebamedrar.transactions.OwnerTransactions;
import com.example.pruebamedrar.utils.Util;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;


public class OwnerRegistrationFragment extends Fragment {

    private Context context;

    private TextInputEditText txtId;
    private TextInputEditText txtName;
    private TextInputEditText txtPhone;
    private Button btnRegister;

    public OwnerRegistrationFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_owner_registration, container, false);

        txtId = view.findViewById(R.id.txtId);
        txtName = view.findViewById(R.id.txtName);
        txtPhone = view.findViewById(R.id.txtPhone);
        btnRegister = view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.hideKeyboard(view, context);

                String strId = txtId.getText().toString();
                String strName = txtName.getText().toString();
                String strPhone = txtPhone.getText().toString();

                boolean hasEmpty = false;

                if(TextUtils.isEmpty(strId)) {
                    txtId.setError(getString(R.string.empty_field));
                    hasEmpty = true;
                }

                if(TextUtils.isEmpty(strName)) {
                    txtName.setError(getString(R.string.empty_field));
                    hasEmpty = true;
                }

                if(TextUtils.isEmpty(strPhone)) {
                    txtPhone.setError(getString(R.string.empty_field));
                    hasEmpty = true;
                }

                if(hasEmpty) {
                    return;
                }

                if(OwnerTransactions.hasOwner(context, Integer.valueOf(strId))) {
                    Snackbar.make(btnRegister, getString(R.string.owner_exists), 2000).show();
                    return;
                }

                Owner owner = new Owner(Integer.valueOf(strId), strName, strPhone);
                OwnerTransactions.insertOwner(context, owner);

                AdviceDialog adviceDialog = AdviceDialog.newInstance(getString(R.string.owner_successful));
                adviceDialog.show(getChildFragmentManager(), "advice");
            }
        });

        return view;
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
}

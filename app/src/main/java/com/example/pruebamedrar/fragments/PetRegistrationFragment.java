package com.example.pruebamedrar.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.pruebamedrar.R;
import com.example.pruebamedrar.beans.Owner;
import com.example.pruebamedrar.beans.Pet;
import com.example.pruebamedrar.dialogs.AdviceDialog;
import com.example.pruebamedrar.dialogs.SearchDialog;
import com.example.pruebamedrar.transactions.PetTransactions;
import com.google.android.material.snackbar.Snackbar;

public class PetRegistrationFragment extends Fragment {

    private Context context;

    private EditText txtId;
    private EditText txtName;
    private Spinner spnType;
    private EditText txtAge;
    private EditText txtBreed;
    private EditText txtOwner;
    private Button btnRegister;

    private SearchDialog searchDialog;

    private Owner owner;

    public PetRegistrationFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet_registration, container, false);

        txtId = view.findViewById(R.id.txtId);
        txtName = view.findViewById(R.id.txtName);
        spnType = view.findViewById(R.id.spnType);
        txtAge = view.findViewById(R.id.txtAge);
        txtBreed = view.findViewById(R.id.txtBreed);
        txtOwner = view.findViewById(R.id.txtOwner);
        btnRegister = view.findViewById(R.id.btnRegister);

        spnType = view.findViewById(R.id.spnType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.pet_types, R.layout.style_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnType.setAdapter(adapter);

        txtOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchDialog = SearchDialog.newInstance("owner");
                searchDialog.show(getChildFragmentManager(), "search");
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strId = txtId.getText().toString();
                String strName = txtName.getText().toString();
                boolean hasType = spnType.getSelectedItemPosition() != 0;
                String strAge = txtAge.getText().toString();
                String strBreed = txtBreed.getText().toString();
                String strOwner = txtOwner.getText().toString();

                boolean hasEmpty = false;

                if (TextUtils.isEmpty(strId)) {
                    txtId.setError(getString(R.string.empty_field));
                    hasEmpty = true;
                }

                if (TextUtils.isEmpty(strName)) {
                    txtName.setError(getString(R.string.empty_field));
                    hasEmpty = true;
                }

                if (!hasType) {
                    Snackbar.make(btnRegister, getString(R.string.pet_type), 2000).show();
                    hasEmpty = true;
                }

                if (TextUtils.isEmpty(strAge)) {
                    txtAge.setError(getString(R.string.empty_field));
                    hasEmpty = true;
                }

                if (TextUtils.isEmpty(strBreed)) {
                    txtBreed.setError(getString(R.string.empty_field));
                    hasEmpty = true;
                }

                if (TextUtils.isEmpty(strOwner)) {
                    txtOwner.setError(getString(R.string.empty_field));
                    hasEmpty = true;
                }

                if (hasEmpty) {
                    return;
                }

                if (PetTransactions.hasPet(context, Integer.valueOf(strId))) {
                    Snackbar.make(btnRegister, getString(R.string.pet_exists), 2000).show();
                    return;
                }

                Pet pet = new Pet();
                pet.setId(Integer.valueOf(strId));
                pet.setName(strName);
                pet.setType(spnType.getSelectedItem().toString());
                pet.setAge(Integer.valueOf(strAge));
                pet.setBreed(strBreed);
                pet.setOwner(owner);

                PetTransactions.insertPet(context, pet);

                AdviceDialog adviceDialog = AdviceDialog.newInstance(getString(R.string.pet_successful));
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

    public void selectOwner(Owner owner) {
        this.owner = owner;
        txtOwner.setText(owner.getName());
        searchDialog.dismiss();
    }
}

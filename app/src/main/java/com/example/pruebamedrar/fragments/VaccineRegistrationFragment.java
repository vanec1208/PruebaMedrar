package com.example.pruebamedrar.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pruebamedrar.R;
import com.example.pruebamedrar.beans.Owner;
import com.example.pruebamedrar.beans.Pet;
import com.example.pruebamedrar.beans.PetVaccines;
import com.example.pruebamedrar.beans.Vaccine;
import com.example.pruebamedrar.dialogs.AdviceDialog;
import com.example.pruebamedrar.dialogs.SearchDialog;
import com.example.pruebamedrar.transactions.VaccineTransactions;

public class VaccineRegistrationFragment extends Fragment {

    private Context context;

    private EditText txtPet;
    private EditText txtName;
    private TextView lblDate;
    private EditText txtDose;
    private Button btnRegister;

    private PetVaccines petVaccines;

    private SearchDialog searchDialog;

    public VaccineRegistrationFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vaccine_registration, container, false);

        txtPet = view.findViewById(R.id.txtPet);
        txtName = view.findViewById(R.id.txtName);
        lblDate = view.findViewById(R.id.lblDate);
        txtDose = view.findViewById(R.id.txtDose);
        btnRegister = view.findViewById(R.id.btnRegister);

        txtPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchDialog = SearchDialog.newInstance("vaccine");
                searchDialog.show(getChildFragmentManager(), "search");
            }
        });

        lblDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String date = datePicker.getYear() + "-" +
                                (datePicker.getMonth() + 1 < 10 ? "0" : "") +
                                (datePicker.getMonth() + 1) + "-" +
                                (datePicker.getDayOfMonth() < 10  ? "0" : "") +
                                datePicker.getDayOfMonth();
                        lblDate.setText(date);
                    }
                }, 2020, 1, 0);
                datePickerDialog.show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strPet = txtPet.getText().toString();
                String strName = txtName.getText().toString();
                String strDate = lblDate.getText().toString();
                String strDose = txtDose.getText().toString();

                boolean hasEmpty = false;

                if (TextUtils.isEmpty(strPet)) {
                    txtPet.setError(getString(R.string.empty_field));
                    hasEmpty = true;
                }

                if (TextUtils.isEmpty(strName)) {
                    txtName.setError(getString(R.string.empty_field));
                    hasEmpty = true;
                }

                if (TextUtils.isEmpty(strDate)) {
                    lblDate.setError(getString(R.string.empty_field));
                    hasEmpty = true;
                }

                if (TextUtils.isEmpty(strDose)) {
                    txtDose.setError(getString(R.string.empty_field));
                    hasEmpty = true;
                }

                if (hasEmpty) {
                    return;
                }

                Vaccine vaccine = new Vaccine();
                vaccine.setName(strName);
                vaccine.setDate(strDate);
                vaccine.setDose(strDose);
                vaccine.setPetId(petVaccines.getPetId());

                VaccineTransactions.insertVaccine(context, vaccine);

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

    public void selectPet(PetVaccines petVaccines) {
        this.petVaccines = petVaccines;
        txtPet.setText(petVaccines.getPetName());
        searchDialog.dismiss();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

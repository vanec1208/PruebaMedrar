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
import android.widget.TextView;

import com.example.pruebamedrar.R;
import com.example.pruebamedrar.adapters.OwnerSearchAdapter;
import com.example.pruebamedrar.adapters.VaccinesSearchAdapter;
import com.example.pruebamedrar.beans.Owner;
import com.example.pruebamedrar.beans.Pet;
import com.example.pruebamedrar.beans.PetVaccines;
import com.example.pruebamedrar.fragments.PetRegistrationFragment;
import com.example.pruebamedrar.fragments.VaccineRegistrationFragment;
import com.example.pruebamedrar.transactions.OwnerTransactions;
import com.example.pruebamedrar.transactions.PetTransactions;

import java.util.ArrayList;

public class SearchDialog extends DialogFragment implements
        OwnerSearchAdapter.OnFragmentListener,
        VaccinesSearchAdapter.OnFragmentListener{
    private static  final String SEARCH_TYPE = "search_type";

    private Context context;

    private TextView lblTitle;
    private EditText txtSearch;
    private RecyclerView recyclerView;
    private OwnerSearchAdapter ownerSearchAdapter;
    private VaccinesSearchAdapter vaccinesSearchAdapter;
    private String searchType;

    private ArrayList<Owner> listOwners = new ArrayList<>();
    private ArrayList<PetVaccines> listVaccines = new ArrayList<>();

    public SearchDialog() {

    }

    public static SearchDialog newInstance(String searchType) {
        SearchDialog fragment = new SearchDialog();
        Bundle args = new Bundle();
        args.putString(SEARCH_TYPE, searchType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            searchType = getArguments().getString(SEARCH_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_dialog, container, false);

        lblTitle = view.findViewById(R.id.lblTitle);
        txtSearch = view.findViewById(R.id.txtSearch);
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        if(searchType.equals("owner")) {
            lblTitle.setText(getString(R.string.search_title));
            txtSearch.setHint(getString(R.string.owner_search));
            ownerSearchAdapter = new OwnerSearchAdapter(this, listOwners);
            recyclerView.setAdapter(ownerSearchAdapter);
        } else {
            lblTitle.setText(getString(R.string.pet_search_title));
            txtSearch.setHint(getString(R.string.pet_search));
            vaccinesSearchAdapter = new VaccinesSearchAdapter(listVaccines, this);
            recyclerView.setAdapter(vaccinesSearchAdapter);
        }

        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterAdapter(editable.toString());
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

    public void filterAdapter(String text) {
        if(searchType.equals("owner")) {
            listOwners = new ArrayList<>();

            if (TextUtils.isEmpty(text)) {
                ownerSearchAdapter.setList(listOwners);
                ownerSearchAdapter.notifyDataSetChanged();
            } else {
                listOwners = OwnerTransactions.getOwners(context, text);
                ownerSearchAdapter.setList(listOwners);
                ownerSearchAdapter.notifyDataSetChanged();
            }
        } else {
            listVaccines = new ArrayList<>();

            if (TextUtils.isEmpty(text)) {
                vaccinesSearchAdapter.setList(listVaccines);
                vaccinesSearchAdapter.notifyDataSetChanged();
            } else {
                listVaccines = PetTransactions.getPetVaccines(context, text);
                vaccinesSearchAdapter.setList(listVaccines);
                vaccinesSearchAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void selectPet(PetVaccines petVaccines) {
        ((VaccineRegistrationFragment) getParentFragment()).selectPet(petVaccines);
    }

    @Override
    public void selectOwner(Owner owner) {
        ((PetRegistrationFragment) getParentFragment()).selectOwner(owner);
    }
}

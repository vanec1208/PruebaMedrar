package com.example.pruebamedrar.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.example.pruebamedrar.adapters.PetSearchAdapter;
import com.example.pruebamedrar.beans.Pet;
import com.example.pruebamedrar.dialogs.AdviceDialog;
import com.example.pruebamedrar.transactions.PetTransactions;

import java.util.ArrayList;

public class PetSearchFragment extends Fragment implements PetSearchAdapter.OnFragmentListener {

    private Context context;

    private EditText txtSearch;
    private RecyclerView recyclerView;
    private PetSearchAdapter petSearchAdapter;

    private ArrayList<Pet> listPets = new ArrayList<>();

    public PetSearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet_search, container, false);

        txtSearch = view.findViewById(R.id.txtSearch);
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);

        petSearchAdapter = new PetSearchAdapter(context, listPets, null);
        recyclerView.setAdapter(petSearchAdapter);
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
                listPets = new ArrayList<>();
                String text = editable.toString();

                if (TextUtils.isEmpty(text)) {
                    petSearchAdapter.setList(listPets);
                    petSearchAdapter.notifyDataSetChanged();
                } else {
                    listPets = PetTransactions.getPets(context, text);
                    petSearchAdapter.setList(listPets);
                    petSearchAdapter.notifyDataSetChanged();
                }
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

    @Override
    public void selectPet(Pet pet) {
        PetTransactions.deletePet(context, pet.getId());
        AdviceDialog adviceDialog = AdviceDialog.newInstance(getString(R.string.delete_successful));
        adviceDialog.show(getChildFragmentManager(), "advice");
    }
}

package com.example.pruebamedrar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.pruebamedrar.ContainerType;
import com.example.pruebamedrar.R;
import com.example.pruebamedrar.fragments.OwnerRegistrationFragment;
import com.example.pruebamedrar.fragments.PetRegistrationFragment;
import com.example.pruebamedrar.fragments.PetSearchFragment;
import com.example.pruebamedrar.fragments.VaccineRegistrationFragment;

public class ContainerActivity extends AppCompatActivity {
    public static final String CONTAINER_TYPE = "container_type";

    public int containerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        if(getIntent().getExtras() != null) {
            containerType = getIntent().getIntExtra(CONTAINER_TYPE, 0);
            loadFragment();
        }
    }

    public void loadFragment() {
        Fragment fragment = null;

        switch (containerType) {
            case ContainerType.OWNER_REGISTRATION:
                fragment = new OwnerRegistrationFragment();
                break;

            case ContainerType.PET_REGISTRATION:
                fragment = new PetRegistrationFragment();
                break;

            case ContainerType.PET_SEARCH:
                fragment = new PetSearchFragment();
                break;

            case ContainerType.VACCINE_REGISTRATION:
                fragment = new VaccineRegistrationFragment();
                break;
        }

        if(fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
        }
    }
}

package com.example.pruebamedrar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pruebamedrar.ContainerType;
import com.example.pruebamedrar.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View view) {
        int containerType = 0;

        switch (view.getId()) {
            case R.id.btnOwnerRegistration:
                containerType = ContainerType.OWNER_REGISTRATION;
                break;

            case R.id.btnPetRegistration:
                containerType = ContainerType.PET_REGISTRATION;
                break;

            case R.id.btnPetSearch:
                containerType = ContainerType.PET_SEARCH;
                break;

            case R.id.btnVaccineRegistration:
                containerType = ContainerType.VACCINE_REGISTRATION;
                break;

            case R.id.btnFinish:
                containerType = ContainerType.FINISH;
                finish();
                break;

        }

        if(containerType != ContainerType.FINISH) {
            Intent intent = new Intent(this, ContainerActivity.class);
            intent.putExtra(ContainerActivity.CONTAINER_TYPE, containerType);
            startActivity(intent);
        }
    }
}

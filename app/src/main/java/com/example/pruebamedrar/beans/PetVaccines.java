package com.example.pruebamedrar.beans;

public class PetVaccines {
    private int petId;
    private String petName;
    private String vaccines;

    public PetVaccines() {
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getVaccines() {
        return vaccines;
    }

    public void setVaccines(String vaccines) {
        this.vaccines = vaccines;
    }

    @Override
    public String toString() {
        return "PetVaccines{" +
                "petId=" + petId +
                ", petName='" + petName + '\'' +
                ", vaccines='" + vaccines + '\'' +
                '}';
    }
}

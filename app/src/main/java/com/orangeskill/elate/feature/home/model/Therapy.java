package com.orangeskill.elate.feature.home.model;

import java.util.Arrays;
import java.util.List;

public class Therapy {


    private TherapyCategory therapyCategories;

    public TherapyCategory getTherapyCategories() {
        return therapyCategories;
    }

    public void setTherapyCategories(TherapyCategory therapyCategories) {
        this.therapyCategories = therapyCategories;
    }

    @Override
    public String toString() {
        return "Therapy{" +
                "therapyCategories=" + therapyCategories +
                '}';
    }
}



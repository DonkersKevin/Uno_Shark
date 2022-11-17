package be.switchfully.uno_shark.security;

import com.google.common.collect.Lists;

import java.util.List;

import static be.switchfully.uno_shark.security.Feature.*;

public enum Role {
    MEMBER("member", GET_ALL_MEMBERS),
    MANAGER("manager", ADD_PARKINGLOT);

    private final String label;
    private final List<Feature> featureList;

    Role(String label, Feature... featureList) {
        this.label = label;
        this.featureList = Lists.newArrayList(featureList);
    }

    public List<Feature> getFeatures() {
        return featureList;
    }

    public String getLabel() {
        return label;
    }
}

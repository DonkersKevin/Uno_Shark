package be.switchfully.uno_shark.security;

import com.google.common.collect.Lists;

import java.util.List;

import static be.switchfully.uno_shark.security.Feature.*;

public enum Role {
    MEMBER("member",
            CLOSE_INVOICE,
            SELECT_MEMBERSHIP_LEVEL,
            START_ALLOCATION_PARKINGSPOT,
            STOP_ALLOCATION_PARKINGSPOT,
            GET_MONTHLY_INVOICE),

    MANAGER("manager",
            CREATE_DIVISION,
            GET_ALL_DIVISIONS,
            GET_DIVISION,
            CREATE_SUBDIVISION,
            CREATE_PARKINGLOT,
            GET_ALL_PARKINGLOTS,
            GET_PARKINGLOT,
            GET_ALL_MEMBERS,
            GET_MEMBER,
            GET_ALL_PARKINGALLOCATIONS,
            GET_ALL_PARKINGALLOCATIONS_FOR_MEMBER,
            GET_ALL_PARKINGALLOCATIONS_FOR_PARKINGLOT,
            GET_ALL_PARKINGALLOCATIONS_DURATION,
            GET_ALL_INVOICES,
            CLOSE_INVOICE);

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

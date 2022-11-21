package be.switchfully.uno_shark.domain.person;

import java.math.BigDecimal;

public enum MembershipLevel {
    BRONZE(0.00,0,4),
    SILVER(10.00,20,6),
    GOLD(40.00,30,24);

    private final BigDecimal monthlyCostInEuro;

    private final int priceReductionPercent;

    private final int maxHours;

    MembershipLevel(Double monthlyCostInEuro, int priceReduction, int maxHours) {
        this.monthlyCostInEuro = new BigDecimal(monthlyCostInEuro);
        this.priceReductionPercent = priceReduction;
        this.maxHours = maxHours;
    }

    public BigDecimal getMonthlyCostInEuro() {
        return monthlyCostInEuro;
    }

    public double getPriceReduction() {
        return priceReductionPercent;
    }

    public int getMaxHours() {
        return maxHours;
    }
}

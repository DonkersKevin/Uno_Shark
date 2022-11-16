package be.switchfully.uno_shark.domain.parking.util;

import javax.persistence.Embeddable;
import java.util.Currency;

@Embeddable
public class Price {
    private double amount;
    private Currency currency;
}

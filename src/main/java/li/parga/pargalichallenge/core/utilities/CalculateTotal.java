package li.parga.pargalichallenge.core.utilities;

import li.parga.pargalichallenge.entities.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;

import java.util.Map;
@Getter
public class CalculateTotal {
    private Map<String,Double> exchangeRates;

    public CalculateTotal(Map<String, Double> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    public CalculateTotal(){
        this.exchangeRates = new HashMap<>();
        exchangeRates.put("USD",16.57);
        exchangeRates.put("EUR",17.73);
        exchangeRates.put("TRY",1.0);
    }

    public double calculate(List<Account> accounts){
        double total = 0;
        for (int i = 0; i < accounts.size(); i++ ) {
            total += accounts.get(i).getBalance() * exchangeRates.get(accounts.get(i).getCurrency());
        }

        return total;
    }


}

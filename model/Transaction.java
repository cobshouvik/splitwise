import java.util.*;

public class Transaction {
    String payer;
    Map<String, Double> payeeDetails;

    public Transaction(String payer) {
        this.payer = payer;
        this.payeeDetails = new HashMap<>();
    }

    public String getPayer() {
        return this.payer;
    }

    public void addPayeeDetails(String payeeId, double amount) {
        payeeDetails.put(payeeId, amount);
    }

    public Map<String, Double> getPayeeDetails() {
        return this.payeeDetails;
    }
}
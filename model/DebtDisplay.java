public class DebtDisplay{
    String owedBy;
    String owedTo;
    double amount;

    public DebtDisplay(String owedBy, String owedTo, double amount) {
        this.owedBy = owedBy;
        this.owedTo = owedTo;
        this.amount = amount;
    }

    public String toString() {
        //User4 owes User1: 250
        return String.format("%s owes %s: %s", this.owedBy, this.owedTo, this.amount);
    }
}
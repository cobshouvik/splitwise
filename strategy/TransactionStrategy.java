public interface TransactionStrategy {
    public Transaction processTransaction(String transactionLedger);
}
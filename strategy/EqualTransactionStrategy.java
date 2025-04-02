public class EqualTransactionStrategy implements TransactionStrategy {
    public Transaction processTransaction(String transactionLedger) {
        //EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
        String[] ledgerEntries = transactionLedger.split(" ");
        int countOfPayee = Integer.parseInt(ledgerEntries[3]);
        int totalAmount = Integer.parseInt(ledgerEntries[2]);
        String payer = ledgerEntries[1];
        Transaction transaction = new Transaction(payer);
        double payeeAmount = Math.round(((double)totalAmount/countOfPayee)*100)/100.0;
        for(int i=0;i<countOfPayee;i++){
            transaction.addPayeeDetails(ledgerEntries[4+i], payeeAmount);
        }
        return transaction;
    }
}
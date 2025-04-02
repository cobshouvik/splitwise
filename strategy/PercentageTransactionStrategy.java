public class PercentageTransactionStrategy implements TransactionStrategy {
    public Transaction processTransaction(String transactionLedger) {
        //EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
        String[] ledgerEntries = transactionLedger.split(" ");
        int countOfPayee = Integer.parseInt(ledgerEntries[3]);
        double totalAmount = Double.parseDouble(ledgerEntries[2]);
        String payer = ledgerEntries[1];
        Transaction transaction = new Transaction(payer);
        double contriTotal = 0;
        for(int i=0;i<countOfPayee;i++) {
            double percentage = Double.parseDouble(ledgerEntries[5+countOfPayee+i]);
            transaction.addPayeeDetails(ledgerEntries[4+i], (double)totalAmount*percentage/100);
            contriTotal += percentage;
        }
        if(contriTotal != 100) {
            System.out.println("Validation failed : Exact contributions do not add up to total amount");
            return null;
        }
        return transaction;
    }
}
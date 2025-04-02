public class ExactTransactionStrategy implements TransactionStrategy {
    public Transaction processTransaction(String transactionLedger) {
        //EXPENSE u1 1250 2 u2 u3 EXACT 370 880
        String[] ledgerEntries = transactionLedger.split(" ");
        int countOfPayee = Integer.parseInt(ledgerEntries[3]);
        double totalAmount = Double.parseDouble(ledgerEntries[2]);
        String payer = ledgerEntries[1];
        Transaction transaction = new Transaction(payer);
        double contriTotal = 0;
        for(int i=0;i<countOfPayee;i++) {
            transaction.addPayeeDetails(ledgerEntries[4+i], Double.parseDouble(ledgerEntries[5+countOfPayee+i]));
            contriTotal += Double.parseDouble(ledgerEntries[5+countOfPayee+i]);
        }
        if(contriTotal != totalAmount) {
            System.out.println("Validation failed : Exact contributions do not add up to total amount");
            return null;
        }
        return transaction;
    }
}
public class SplitwiseApplication {
    static TransactionManager transactionManager;

    public static void main(String[] args) {
        transactionManager = new TransactionManager();
        transactionManager.show();
        transactionManager.show("u1");
        transactionManager.addExpense("EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL");
        transactionManager.show("u4");
        transactionManager.show("u1");
        transactionManager.addExpense("EXPENSE u1 1250 2 u2 u3 EXACT 370 880");
        transactionManager.show();
        transactionManager.addExpense("EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20");
        transactionManager.show("u1");
        transactionManager.show();
        transactionManager.addExpense("EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 20 20 20 20");
        // transactionManager.addExpense("EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 20 20 20 20");
    }
}
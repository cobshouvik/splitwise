import java.util.*;

public class TransactionManager {
    ActiveDebtRepository activeDebtRepository;
    Map<String, User> userMap;
    TransactionRepository transactionRepository;
    Map<TransactionType, TransactionStrategy> transactionStrategyMap;

    public TransactionManager() {
        this.activeDebtRepository = new ActiveDebtRepository();
        this.userMap = new HashMap<>();
        this.transactionRepository = new TransactionRepository();
        this.transactionStrategyMap = Map.of(
            TransactionType.EQUAL , TransactionStrategyFactory.getTransactionStrategy(TransactionType.EQUAL),
            TransactionType.EXACT , TransactionStrategyFactory.getTransactionStrategy(TransactionType.EXACT),
            TransactionType.PERCENT , TransactionStrategyFactory.getTransactionStrategy(TransactionType.PERCENT)
        );
    }

    public void show() {
        display(this.activeDebtRepository.getDebtDisplay());
    }

    public void show(String userName) {
        display(this.activeDebtRepository.getDebtDisplay(userName));
    }

    private void display(List<DebtDisplay> debtDisplayList) {
        if(debtDisplayList.isEmpty()) {
            System.out.println("No balance");
        } else {
            debtDisplayList.forEach(d -> System.out.println(d));
        }
        System.out.println("----------------------------------------------------------");
    }

    public void addExpense(String ledger) {
        Transaction transaction = parseTransaction(ledger);
        if(transaction==null) {
            System.out.println("Invalid transaction ledger : " + ledger);
            return;
        }
        this.transactionRepository.addTransaction(transaction);
        this.activeDebtRepository.handleTransaction(transaction);

    }

    private Transaction parseTransaction(String ledger) {
        Transaction transaction = null;
        for(Map.Entry<TransactionType, TransactionStrategy> entry : transactionStrategyMap.entrySet()) {
            if(ledger.contains(entry.getKey().name())) {
                transaction = entry.getValue().processTransaction(ledger);
                break;
            }
        }
        return transaction;
    }

}
import java.util.*;

public class ActiveDebtRepository{
    Map<String, Map<String, Double>> activeDebts;

    public ActiveDebtRepository() {
        this.activeDebts = new HashMap<>();
    }

    public List<DebtDisplay> getDebtDisplay(String user) {
        List<DebtDisplay> debtDisplayList = new ArrayList<>();
        for(Map.Entry<String, Map<String, Double>> entry : activeDebts.entrySet()) {
            String owedBy = entry.getKey();
            Map<String, Double> owedToMap = entry.getValue();
            if(owedBy.equals(user)) {
                for(Map.Entry<String, Double> owedToEntry : owedToMap.entrySet()) {
                    debtDisplayList.add(new DebtDisplay(user, owedToEntry.getKey(), owedToEntry.getValue()));
                }
            } else {
                for(Map.Entry<String, Double> owedToEntry : owedToMap.entrySet()) {
                    if(owedToEntry.getKey().equals(user))
                        debtDisplayList.add(new DebtDisplay(owedBy, user, owedToEntry.getValue()));
                }
            }
        }
        return debtDisplayList;
    }

    public List<DebtDisplay> getDebtDisplay() {
        List<DebtDisplay> debtDisplayList = new ArrayList<>();
        for(Map.Entry<String, Map<String, Double>> entry : activeDebts.entrySet()) {
            String owedBy = entry.getKey();
            Map<String, Double> owedToMap = entry.getValue();
            for(Map.Entry<String, Double> owedToEntry : owedToMap.entrySet()) {
                debtDisplayList.add(new DebtDisplay(owedBy, owedToEntry.getKey(), owedToEntry.getValue()));
            }
        }
        return debtDisplayList;
    }

    public void handleTransaction(Transaction transaction) {
        String payer = transaction.getPayer();
        Map<String, Double> activeDebtOfPayer = activeDebts.getOrDefault(payer, new HashMap<>());
        for(Map.Entry<String, Double> payeeDetailEntry : transaction.getPayeeDetails().entrySet()) {
            String payeeName = payeeDetailEntry.getKey();
            if(payer.equals(payeeName))
                continue;
            double debtAmount = payeeDetailEntry.getValue();
            double existingReverseDebt = activeDebtOfPayer.getOrDefault(payeeName, (double)0);
            if(existingReverseDebt > 0 ) {
                double maxSettledAmount = Math.min(existingReverseDebt, debtAmount);
                settleDebt(payeeName, maxSettledAmount, activeDebtOfPayer);
                debtAmount -= maxSettledAmount;
            }
            if(debtAmount > 0) {
                activeDebts.putIfAbsent(payeeName, new HashMap<>());
                activeDebts.get(payeeName).put(payer, activeDebts.get(payeeName).getOrDefault(payer, (double)0) + debtAmount);
            }
        }
    }

    private void settleDebt(String userName, double amount, Map<String, Double> activeDebtOfPayer) {
        if(!activeDebtOfPayer.containsKey(userName)) {
            return;
        }
        double outstandingAmount = activeDebtOfPayer.get(userName);
        if(outstandingAmount == amount) {
            activeDebtOfPayer.remove(userName);
        } else {
            activeDebtOfPayer.put(userName, outstandingAmount - amount);
        }
    }
}
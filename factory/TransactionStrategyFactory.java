public class TransactionStrategyFactory {
    public static TransactionStrategy getTransactionStrategy(TransactionType type) {
        switch(type) {
            case EQUAL : return new EqualTransactionStrategy();
            case EXACT : return new ExactTransactionStrategy();
            case PERCENT : return new PercentageTransactionStrategy();
            default : return null;
        }
    }
}
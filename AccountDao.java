public interface AccountDao {
        public Account getAccount(int AID);
        public void deposit(int AID, DigitMoney money);
        public void withdraw(int AID, DigitMoney money);
        public void transfer(int AID1,int AID2,DigitMoney money);
        public void exchangeCurrency(int AID,Currency targetCurrency);
}

package kr.codesquad.domain;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoCustomer {
    private int purchaseAmount;
    private final ArrayList<Lotto> lotteries = new ArrayList<>();

    public void putCustomerPurchaseAmount(String purchaseAmountStr) {
        Validator validator = new Validator();
        this.purchaseAmount = validator.validatePurchaseAmount(purchaseAmountStr);
    }

    public void purchaseLotto() {
        IntStream.range(Config.ZERO, getCountOfLotto()).forEach(i -> lotteries.add(new Lotto()));
    }

    public int getCountOfLotto() {
        return purchaseAmount / Config.PRICE_OF_LOTTO;
    }

    public double getEarningsRate(double totalWinAmount) {
        return (totalWinAmount - purchaseAmount) / purchaseAmount * Config.PERCENT;
    }

    public void compareLuckyNumbers(ArrayList<Integer> luckyNumbers, int bonusBall) {
        lotteries.forEach(s -> s.checkLuckyNumbersContain(luckyNumbers, bonusBall));
    }

    @Override
    public String toString() {
        return lotteries.stream()
                .map(Lotto::toString)
                .collect(Collectors.joining(Config.LINE_BREAK));
    }
}

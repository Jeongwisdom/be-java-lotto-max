package kr.codesquad.domain;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoCustomer {
    private int purchaseAmount;
    private int countOfHand;
    private final ArrayList<Lotto> lotteries = new ArrayList<>();

    public void putCustomerPurchaseAmount(String purchaseAmountStr) {
        Validator validator = new Validator();
        this.purchaseAmount = validator.validatePurchaseAmount(purchaseAmountStr);
    }

    public void putCountOfHand(String countOfHandStr) {
        Validator validator = new Validator();
        this.countOfHand = validator.validateCountOfHand(countOfHandStr);
        if(countOfHand > calculateCountOfLotto()) {
            throw new IllegalArgumentException();
        }
    }

    public void purchaseAutoLotto() {
        IntStream.range(Config.ZERO, (calculateCountOfLotto() - countOfHand)).forEach(i -> lotteries.add(new Lotto()));
    }

    public void purchaseHandLotto(ArrayList<Integer> numbers) {
        lotteries.add(new Lotto(numbers));
    }

    public int calculateCountOfLotto() {
        return purchaseAmount / Config.PRICE_OF_LOTTO;
    }

    public double calculateEarningsRate(double totalWinAmount) {
        return (totalWinAmount - purchaseAmount) / purchaseAmount * Config.PERCENT;
    }

    public void compareLuckyNumbers(ArrayList<Integer> luckyNumbers, int bonusBall) {
        lotteries.forEach(s -> s.checkLuckyNumbersContain(luckyNumbers, bonusBall));
    }

    @Override
    public String toString() {
        return "수동으로 " + countOfHand + "장, 자동으로 " + (calculateCountOfLotto() - countOfHand) + "장을 구매했습니다.\n"
                + lotteries.stream()
                .map(Lotto::toString)
                .collect(Collectors.joining(Config.LINE_BREAK));
    }
}

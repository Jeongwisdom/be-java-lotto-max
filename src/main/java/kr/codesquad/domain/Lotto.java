package kr.codesquad.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {
    private final ArrayList<Integer> lotto;
    boolean bonusBall;

    public Lotto() {
        ArrayList<Integer> numbers = (ArrayList<Integer>) IntStream.rangeClosed(Config.MIN_LOTTO_NUMBER, Config.MAX_LOTTO_NUMBER)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        lotto = (ArrayList<Integer>) numbers.stream().limit(Config.LOTTO_NUMBERS_SIZE).collect(Collectors.toList());
        Collections.sort(lotto);
        this.bonusBall = false;
    }

    public Lotto(ArrayList<Integer> numbers) {
        lotto = numbers;
        Collections.sort(lotto);
        this.bonusBall = false;
    }

    public void checkLuckyNumbersContain(ArrayList<Integer> luckyNumbers, int bonusBall) {
        int countOfMatch = Config.ZERO;
        for (Integer integer : lotto) {
            countOfMatch = increaseCountOfMatch(luckyNumbers, integer, countOfMatch);
        }
        checkBonusBallContain(bonusBall);
        Rank.checkContainNumber(countOfMatch, this.bonusBall);
    }

    private void checkBonusBallContain(int bonusBall) {
        if(lotto.contains(bonusBall)) {
            this.bonusBall = true;
        }
    }

    private int increaseCountOfMatch(ArrayList<Integer> luckyNumbers, Integer integer, int countOfMatch) {
        if (luckyNumbers.contains(integer)) {
            countOfMatch++;
        }
        return countOfMatch;
    }

    @Override
    public String toString() {
        return lotto.toString();
    }
}

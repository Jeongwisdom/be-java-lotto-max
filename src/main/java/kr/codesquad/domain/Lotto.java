package kr.codesquad.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {
    private final ArrayList<Integer> lotto;

    public Lotto() {
        ArrayList<Integer> numbers = (ArrayList<Integer>) IntStream.rangeClosed(Config.MIN_LOTTO_NUMBER, Config.MAX_LOTTO_NUMBER)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        lotto = (ArrayList<Integer>) numbers.stream().limit(Config.LOTTO_BALL_NUMBER).collect(Collectors.toList());
        Collections.sort(lotto);
    }

    public void checkLuckyNumbersContain(ArrayList<Integer> luckyNumbers) {
        int countOfMatch = Config.ZERO;
        for (Integer integer : lotto) {
            countOfMatch = increaseCountOfMatch(luckyNumbers, integer, countOfMatch);
        }
        Rank.checkContainNumber(countOfMatch);
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

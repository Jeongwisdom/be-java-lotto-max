package kr.codesquad.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {
    private final ArrayList<Integer> lotto;

    public Lotto() {
        ArrayList<Integer> numbers = (ArrayList<Integer>) IntStream.rangeClosed(1, 45)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        lotto = (ArrayList<Integer>) numbers.stream().limit(6).collect(Collectors.toList());
        Collections.sort(lotto);
    }

    public void checkLuckyNumbersContain(ArrayList<Integer> luckyNumbers) {
        int countOfMatch = 0;
        for (Integer integer : lotto) {
            if (luckyNumbers.contains(integer)) {
                countOfMatch++;
            }
        }
        Rank.increaseContainNumber(countOfMatch);
    }

    @Override
    public String toString() {
        return lotto.toString();
    }
}
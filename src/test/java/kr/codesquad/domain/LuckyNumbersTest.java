package kr.codesquad.domain;

import kr.codesquad.view.LottoInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class LuckyNumbersTest {
    @DisplayName("유효한 당첨 번호인지 확인하는 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1 2 3 4 5 6,true"
            , "1 2 3,false"
            , "50 2 3 4 5 6,false"
            , "A 2 3 4 5 6,false"})
    public void validateLuckyNumbersTest(String input, boolean expected) {
        Validator validator = new Validator();
        List<String> answer = Arrays.stream(input.split(" ")).collect(Collectors.toList());

        boolean result = validator.checkLottoNumbers(answer);

        assertThat(result).isEqualTo(expected);
    }
}

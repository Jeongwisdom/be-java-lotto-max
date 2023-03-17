package kr.codesquad.view;

import kr.codesquad.domain.LottoCustomer;
import kr.codesquad.domain.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoInputTest {
    @DisplayName("손님의 구입 금액 입력에 따른 결과 확인")
    @ParameterizedTest
    @CsvSource(value = {"3000,true", "a,false", "0,false"})
    public void InputLottoTest(String input, boolean expected) {
        LottoCustomer lottoCustomer = new LottoCustomer();
        boolean result = Validator.checkPurchaseAmount(lottoCustomer, input);

        assertThat(result).isEqualTo(expected);
    }
}

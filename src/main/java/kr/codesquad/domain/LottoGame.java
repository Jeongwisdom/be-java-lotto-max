package kr.codesquad.domain;

import kr.codesquad.view.LottoInput;
import kr.codesquad.view.LottoOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class LottoGame {
    public void start() throws IOException {
        LottoCustomer lottoCustomer = new LottoCustomer();
        LottoInput.inputPurchaseAmount(lottoCustomer);
        lottoCustomer.purchaseLotto();
        LottoOutput.printLotto(lottoCustomer);

        ArrayList<Integer> luckyNumbers = LottoInput.inputLuckyNumber();
        Collections.sort(luckyNumbers);
        int bonusBall = LottoInput.inputBonusBall(luckyNumbers);

        lottoCustomer.compareLuckyNumbers(luckyNumbers);
        LottoOutput.printLottoStats(lottoCustomer);
    }
}

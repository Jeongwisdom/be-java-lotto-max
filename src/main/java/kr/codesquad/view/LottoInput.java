package kr.codesquad.view;

import kr.codesquad.domain.Config;
import kr.codesquad.domain.LottoCustomer;
import kr.codesquad.domain.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoInput {
    public static String inputAnswer(int index) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] question = {Config.ASK_PURCHASE_AMOUNT
                , Config.ASK_COUNT_OF_HAND
                , Config.ASK_HAND_PURCHASE_NUMBERS
                , Config.ASK_WINNING_NUMBERS
                , Config.ASK_BONUS_BALL};
        System.out.println(question[index]);
        return br.readLine();
    }

    public static void inputPurchaseAmount(LottoCustomer lottoCustomer) throws IOException {
        boolean validPurchaseAmount = false;
        while (!validPurchaseAmount) {
            String purchaseAmountStr = inputAnswer(Config.ASK_PURCHASE_AMOUNT_NUMBER);
            validPurchaseAmount = Validator.checkPurchaseAmount(lottoCustomer, purchaseAmountStr);
        }
    }

    public static ArrayList<Integer> inputLuckyNumber() throws IOException {
        Validator validator = new Validator();
        boolean validLuckyNumbers = false;
        Set<String> answer = new HashSet<>();
        while (!validLuckyNumbers) {
            answer = Arrays.stream(inputAnswer(Config.ASK_WINNING_NUMBERS_NUMBER)
                    .replaceAll(" ", "")
                    .split(","))
                    .collect(Collectors.toSet());
            validLuckyNumbers = validator.checkLuckyNumbers(answer);
        }
        return (ArrayList<Integer>) answer.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int inputBonusBall(ArrayList<Integer> luckyNumbers) throws IOException {
        Validator validator = new Validator();
        String bonusBall = null;
        boolean validBonusBall = false;
        while (!validBonusBall) {
            bonusBall = inputAnswer(Config.ASK_BONUS_BALL_NUMBER);
            validBonusBall = validator.checkBonusBall(bonusBall, luckyNumbers);
        }
        return Integer.parseInt(bonusBall);
    }
}

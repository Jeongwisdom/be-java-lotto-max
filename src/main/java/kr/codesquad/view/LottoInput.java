package kr.codesquad.view;

import kr.codesquad.domain.Config;
import kr.codesquad.domain.LottoCustomer;
import kr.codesquad.domain.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class LottoInput {
    private static String inputAnswer(int index) throws IOException {
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

    public static int inputCountOfHand(LottoCustomer lottoCustomer) throws IOException {
        boolean validCountOfHand = false;
        String countOfHandStr = null;
        while (!validCountOfHand) {
            countOfHandStr = inputAnswer(Config.ASK_COUNT_OF_HAND_NUMBER);
            validCountOfHand = Validator.checkCountOfHand(lottoCustomer, countOfHandStr);
        }
        return Integer.parseInt(countOfHandStr);
    }

    public static void inputHandPurchaseNumbers(LottoCustomer lottoCustomer, int countOfHand) throws IOException {
        Validator validator = new Validator();
        boolean validHandPurchaseNumbers = false;
        List<String> handPurchaseNumbersStr = null;
        for (int i = 0; i < countOfHand; i++) {
            while (!validHandPurchaseNumbers) {
                handPurchaseNumbersStr = Arrays.stream(inputAnswer(Config.ASK_HAND_PURCHASE_NUMBERS_NUMBER)
                                .replaceAll(" ", "")
                                .split(","))
                        .distinct()
                        .collect(Collectors.toList());
                validHandPurchaseNumbers = validator.checkLottoNumbers(handPurchaseNumbersStr);
            }
            validHandPurchaseNumbers = false;
            ArrayList<Integer> handPurchaseNumbers = (ArrayList<Integer>) handPurchaseNumbersStr.stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            lottoCustomer.purchaseHandLotto(handPurchaseNumbers);
        }
    }

    public static ArrayList<Integer> inputLuckyNumber() throws IOException {
        Validator validator = new Validator();
        boolean validLuckyNumbers = false;
        List<String> answer = new ArrayList<>();
        while (!validLuckyNumbers) {
            answer = Arrays.stream(inputAnswer(Config.ASK_WINNING_NUMBERS_NUMBER)
                            .replaceAll(" ", "")
                            .split(","))
                    .distinct()
                    .collect(Collectors.toList());
            validLuckyNumbers = validator.checkLottoNumbers(answer);
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

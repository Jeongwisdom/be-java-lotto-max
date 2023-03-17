package kr.codesquad.domain;

import java.util.ArrayList;
import java.util.Set;

public class Validator {

    public static boolean checkPurchaseAmount(LottoCustomer lottoCustomer, String purchaseAmountStr) {
        try {
            lottoCustomer.putCustomerPurchaseAmount(purchaseAmountStr);
            return true;
        } catch (NumberFormatException e) {
            System.out.println(Config.NOT_INTEGER);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(Config.IS_POSITIVE_NUMBER);
            return false;
        }
    }

    public int validatePurchaseAmount(String purchaseAmountStr) {
        int purchaseAmount = changeInt(purchaseAmountStr);
        isPositiveNum(purchaseAmount);
        return purchaseAmount;
    }

    private int changeInt(String answer) {
        try {
            return Integer.parseInt(answer);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    private void isPositiveNum(int purchaseAmount) {
        if(purchaseAmount <= Config.ZERO) {
            throw new IllegalArgumentException();
        }
    }

    public static boolean checkCountOfHand(LottoCustomer lottoCustomer, String countOfHandStr) {
        try {
            lottoCustomer.putCountOfHand(countOfHandStr);
            return true;
        } catch (NumberFormatException e) {
            System.out.println(Config.NOT_INTEGER);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(Config.IS_POSITIVE_NUMBER);
            return false;
        }
    }

    public int validateCountOfHand(String countOfHandStr) {
        int countOfHand = changeInt(countOfHandStr);
        isPositiveNum(countOfHand);
        return countOfHand;
    }

    public boolean checkLuckyNumbers(Set<String> answer) {
        try {
            createLuckyNumbers(answer);
            return true;
        } catch (NumberFormatException e) {
            System.out.println(Config.NOT_INTEGER);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(Config.LOTTO_NUMBER_LIMIT);
            return false;
        }
    }

    public void createLuckyNumbers(Set<String> answer) {
        try {
            validateLuckyNumbers(answer);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    private void validateLuckyNumbers(Set<String> strings) {
        if(strings.size() != Config.LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException();
        }
        for (String string : strings) {
            limitLottoNumbers(Integer.parseInt(string));
        }
    }

    private void limitLottoNumbers(int luckyNumber) {
        if(luckyNumber < Config.MIN_LOTTO_NUMBER || luckyNumber > Config.MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException();
        }
    }

    public boolean checkBonusBall(String bonusBall, ArrayList<Integer> luckyNumbers) {
        try {
            validateBonusBall(changeInt(bonusBall), luckyNumbers);
            return true;
        } catch (NumberFormatException e) {
            System.out.println(Config.NOT_INTEGER);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(Config.BONUS_BALL_LIMIT);
            return false;
        }
    }

    private void validateBonusBall(int bonusBall, ArrayList<Integer> luckyNumbers) {
        if(luckyNumbers.contains(bonusBall)) {
            throw new IllegalArgumentException();
        }
        limitLottoNumbers(bonusBall);
    }
}

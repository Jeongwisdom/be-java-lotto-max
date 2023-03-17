package kr.codesquad.view;

import kr.codesquad.domain.LottoCustomer;
import kr.codesquad.domain.Rank;

public class LottoOutput {
    public static void printLotto(LottoCustomer lottoCustomer) {
        System.out.println(lottoCustomer);
    }

    public static void printLottoStats(LottoCustomer lottoCustomer) {
        System.out.println("당첨 통계");
        System.out.println("--------------------------------");
        Rank.printLottoStats();
        double totalWinAmount = Rank.calculateTotalWinAmount();
        System.out.println("총 수익률은 " + lottoCustomer.calculateEarningsRate(totalWinAmount) + "% 입니다.");
    }
}

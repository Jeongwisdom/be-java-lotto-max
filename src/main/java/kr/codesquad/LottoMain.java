package kr.codesquad;

import kr.codesquad.domain.LottoGame;

import java.io.IOException;

public class LottoMain {
    public static void main(String[] args) throws IOException {
        LottoGame lottoGame = new LottoGame();
        lottoGame.start();
    }
}
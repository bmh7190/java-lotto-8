package lotto.service;

import java.util.List;
import lotto.domain.WinningLotto;

public class LottoService {

    private static final int PRICE_PER_LOTTO = 1000;

    public WinningLotto createWinningLotto(List<Integer> winningNumbers, int bonusNumber){
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    public int calculateCount(int purchaseAmount) {
        return purchaseAmount / PRICE_PER_LOTTO;
    }


}

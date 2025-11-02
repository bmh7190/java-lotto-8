package lotto.service;

import java.util.List;
import lotto.domain.WinningLotto;

public class LottoService {

    public WinningLotto createWinningLotto(List<Integer> winningNumbers, int bonusNumber){
        return new WinningLotto(winningNumbers, bonusNumber);
    }



}

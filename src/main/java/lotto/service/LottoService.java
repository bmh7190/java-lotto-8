package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.UserLottos;
import lotto.domain.WinningLotto;
import lotto.generator.LottoNumberGenerator;

public class LottoService {

    private static final int PRICE_PER_LOTTO = 1000;
    private final LottoNumberGenerator generator;

    public LottoService(LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public WinningLotto createWinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    public UserLottos createUserLottos(int purchaseAmount) {

        int count = purchaseAmount / PRICE_PER_LOTTO;
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(generator.generateNumbers()));
        }

        return new UserLottos(lottos);
    }

    public int calculateCount(int purchaseAmount) {
        return purchaseAmount / PRICE_PER_LOTTO;
    }


}

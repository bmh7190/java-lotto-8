package lotto.service;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;
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

    public LottoResult calculateResult(UserLottos userLottos, WinningLotto winningLotto) {

        Map<Rank, Integer> result = new EnumMap<>(Rank.class);

        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }

        for (Lotto lotto : userLottos.getLottos()) {
            Rank rank = winningLotto.match(lotto);
            result.put(rank, result.get(rank) + 1);
        }

        return new LottoResult(result);
    }

    public int calculateCount(int purchaseAmount) {
        return purchaseAmount / PRICE_PER_LOTTO;
    }


}

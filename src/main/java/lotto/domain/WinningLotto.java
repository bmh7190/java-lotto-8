package lotto.domain;

import java.util.List;

public class WinningLotto {

    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningLotto(List<Integer> numbers, int bonusNumber) {
        this.winningLotto = new Lotto(numbers);
        this.bonusNumber = bonusNumber;
    }

    public Rank match(Lotto userLotto) {

        int matchCount = (int) userLotto.getNumbers().stream()
                .filter(winningLotto.getNumbers()::contains)
                .count();

        boolean bonusMatched = userLotto.getNumbers().contains(bonusNumber);

        return Rank.of(matchCount, bonusMatched);
    }

}

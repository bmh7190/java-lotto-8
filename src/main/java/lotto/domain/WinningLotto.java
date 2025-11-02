package lotto.domain;

import java.util.List;
import lotto.common.ErrorMessage;

public class WinningLotto {

    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningLotto(List<Integer> numbers, int bonusNumber) {
        this.winningLotto = new Lotto(numbers);
        validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(int bonusNumber) {
        if (winningLotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.of("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다."));
        }
    }

    public Rank match(Lotto userLotto) {

        int matchCount = (int) userLotto.getNumbers().stream()
                .filter(winningLotto.getNumbers()::contains)
                .count();

        boolean bonusMatched = userLotto.getNumbers().contains(bonusNumber);

        return Rank.of(matchCount, bonusMatched);
    }

}

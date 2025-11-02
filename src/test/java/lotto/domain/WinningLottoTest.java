package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    @Test
    void createWinningLotto_bonusDuplicated_throwsException() {
        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("사용자 로또와 비교해 맞은 개수와 보너스 여부로 Rank를 반환한다.")
    @Test
    void match_userLotto_returnsRank() {
        WinningLotto winning = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto user = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        Rank rank = winning.match(user);

        assertThat(rank).isEqualTo(Rank.SECOND);
    }

}

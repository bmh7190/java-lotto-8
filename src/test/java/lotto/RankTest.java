package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("6개 일치하면 1등을 반환한다.")
    @Test
    void of_sixMatches_returnsFirst() {
        Rank rank = Rank.of(6, false);
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @DisplayName("5개 일치하고 보너스가 일치하면 2등을 반환한다.")
    @Test
    void of_fiveMatchesWithBonus_returnsSecond() {
        Rank rank = Rank.of(5, true);
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @DisplayName("5개 일치하고 보너스가 불일치하면 3등을 반환한다.")
    @Test
    void of_fiveMatchesWithoutBonus_returnsThird() {
        Rank rank = Rank.of(5, false);
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @DisplayName("4개 일치하면 4등을 반환한다.")
    @Test
    void of_fourMatches_returnsFourth() {
        Rank rank = Rank.of(4, false);
        assertThat(rank).isEqualTo(Rank.FOURTH);
    }

    @DisplayName("3개 일치하면 5등을 반환한다.")
    @Test
    void of_threeMatches_returnsFifth() {
        Rank rank = Rank.of(3, false);
        assertThat(rank).isEqualTo(Rank.FIFTH);
    }

    @DisplayName("3개 미만으로 일치하면 MISS를 반환한다.")
    @Test
    void of_lessThanThreeMatches_returnsMiss() {
        assertThat(Rank.of(2, false)).isEqualTo(Rank.MISS);
        assertThat(Rank.of(1, true)).isEqualTo(Rank.MISS);
        assertThat(Rank.of(0, false)).isEqualTo(Rank.MISS);
    }
}

package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @DisplayName("Rank별 개수를 전달하면 해당 값을 보관한다.")
    @Test
    void createLottoResult_storesCountsByRank() {
        Map<Rank, Integer> result = new EnumMap<>(Rank.class);
        result.put(Rank.FIRST, 1);
        result.put(Rank.SECOND, 2);

        LottoResult lottoResult = new LottoResult(result);

        assertThat(lottoResult.getCount(Rank.FIRST)).isEqualTo(1);
        assertThat(lottoResult.getCount(Rank.SECOND)).isEqualTo(2);
        assertThat(lottoResult.getCount(Rank.THIRD)).isEqualTo(0); // 없는 건 0
    }

    @DisplayName("총 상금 금액을 계산한다.")
    @Test
    void getTotalPrize_calculatesSumCorrectly() {
        Map<Rank, Integer> result = new EnumMap<>(Rank.class);
        result.put(Rank.FIRST, 1);   // 예: 2_000_000_000
        result.put(Rank.SECOND, 1);  // 예: 30_000_000
        result.put(Rank.FOURTH, 2);  // 예: 50_000 * 2

        LottoResult lottoResult = new LottoResult(result);

        long totalPrize = lottoResult.getTotalPrize();

        long expected = (long) Rank.FIRST.getPrize()
                + Rank.SECOND.getPrize()
                + (long) Rank.FOURTH.getPrize() * 2;

        assertThat(totalPrize).isEqualTo(expected);
    }

    @DisplayName("getWinningCounts는 5등부터 1등 순으로 개수를 반환한다.")
    @Test
    void getWinningCounts_returnsCountsInFixedOrder() {
        Map<Rank, Integer> result = new EnumMap<>(Rank.class);
        result.put(Rank.FIFTH, 1);
        result.put(Rank.FOURTH, 2);
        result.put(Rank.THIRD, 3);
        result.put(Rank.SECOND, 4);
        result.put(Rank.FIRST, 5);

        LottoResult lottoResult = new LottoResult(result);

        List<Integer> counts = lottoResult.getWinningCounts();

        assertThat(counts).containsExactly(1, 2, 3, 4, 5);
    }
}

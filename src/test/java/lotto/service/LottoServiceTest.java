package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.List;
import lotto.domain.Rank;
import lotto.domain.UserLottos;
import lotto.generator.FixLottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoServiceTest {

    @DisplayName("구매 금액에 따라 로또 개수를 생성한다.")
    @Test
    void createUserLottos_byPurchaseAmount() {

        LottoService service = new LottoService(
                new FixLottoNumberGenerator(List.of(1, 2, 3, 4, 5, 6))
        );

        UserLottos userLottos = service.createUserLottos(3000); // 1000원당 1장

        assertThat(userLottos.getLottos()).hasSize(3);
    }

    @DisplayName("당첨 번호와 보너스 번호로 WinningLotto를 생성한다.")
    @Test
    void createWinningLotto_createsDomainObject() {

        LottoService service = new LottoService(
                new FixLottoNumberGenerator(List.of(1, 2, 3, 4, 5, 6))
        );

        var winning = service.createWinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        assertThat(winning).isNotNull();
    }

    @DisplayName("사용자의 모든 로또를 당첨 로또와 비교해 Rank별 개수를 집계한다.")
    @Test
    void calculateResult_countsRanks() {

        LottoService service = new LottoService(
                new FixLottoNumberGenerator(List.of(1, 2, 3, 4, 5, 6))
        );

        var userLottos = service.createUserLottos(2000);

        var winning = service.createWinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        var lottoResult = service.calculateResult(userLottos, winning);

        assertThat(lottoResult.getCount(lotto.domain.Rank.FIRST)).isEqualTo(2);
        assertThat(lottoResult.getCount(lotto.domain.Rank.SECOND)).isZero();
    }

    @DisplayName("총 상금과 구매 금액으로 수익률을 계산한다.")
    @Test
    void calculateYield_returnsProfitRate() {
        LottoService service = new LottoService(
                new FixLottoNumberGenerator(List.of(1, 2, 3, 4, 5, 6))
        );

        var resultMap = new EnumMap<Rank, Integer>(Rank.class);
        resultMap.put(Rank.FIRST, 1); // 2_000_000_000
        var lottoResult = new lotto.domain.LottoResult(resultMap);

        double yield = service.calculateYield(lottoResult, 10_000);

        assertThat(yield).isEqualTo(20_000_000.0);
    }

    @DisplayName("구매 금액이 0원이면 수익률은 0.0을 반환한다.")
    @Test
    void calculateYield_whenPurchaseAmountZero_returnsZero() {
        LottoService service = new LottoService(
                new FixLottoNumberGenerator(List.of(1, 2, 3, 4, 5, 6))
        );

        var result = new lotto.domain.LottoResult(new EnumMap<>(Rank.class));

        double yield = service.calculateYield(result, 0);

        assertThat(yield).isZero();
    }

    @DisplayName("구매 금액으로 로또 장 수를 계산한다.")
    @Test
    void calculateCount_returnsCorrectCount() {
        LottoService service = new LottoService(
                new FixLottoNumberGenerator(List.of(1, 2, 3, 4, 5, 6))
        );

        int count = service.calculateCount(4500);

        assertThat(count).isEqualTo(4);
    }
}


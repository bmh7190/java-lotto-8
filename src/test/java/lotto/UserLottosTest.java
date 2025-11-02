package lotto;

import lotto.domain.Lotto;
import lotto.domain.UserLottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class UserLottosTest {

    @DisplayName("UserLottos는 전달받은 Lotto 리스트를 불변으로 유지한다.")
    @Test
    void createUserLottos_returnsImmutableList() {

        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(7, 8, 9, 10, 11, 12))
        );

        UserLottos userLottos = new UserLottos(lottos);

        assertThatThrownBy(() -> userLottos.getLottos().add(new Lotto(List.of(13, 14, 15, 16, 17, 18))))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("UserLottos는 내부 Lotto 번호 리스트를 반환한다.")
    @Test
    void getLottoNumbers_returnsNumbersOfEachLotto() {

        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(7, 8, 9, 10, 11, 12));
        UserLottos userLottos = new UserLottos(List.of(lotto1, lotto2));

        List<List<Integer>> numbers = userLottos.getLottoNumbers();

        assertThat(numbers)
                .containsExactly(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(7, 8, 9, 10, 11, 12)
                );
    }
}

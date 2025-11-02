package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LottoTest {

    @DisplayName("로또 번호의 개수가 6개를 초과하면 예외가 발생한다.")
    @Test
    void createLotto_exceedSixNumbers_throwsException() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLotto_containsDuplicateNumbers_throwsException() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호 리스트가 null이면 예외가 발생한다.")
    @Test
    void createLotto_withNullNumbers_throwsException() {
        assertThatThrownBy(() -> new Lotto(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호가 비어있습니다.");
    }

    @DisplayName("로또 생성 시 자동으로 정렬된다.")
    @Test
    void createLotto_unsortedNumbers_sortedAutomatically() {
        List<Integer> numbers = Arrays.asList(21, 8, 43, 42, 23, 41);
        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.getNumbers())
                .containsExactly(8, 21, 23, 41, 42, 43);
    }

    @DisplayName("반환된 리스트는 불변이다.")
    @Test
    void getNumbers_tryToModifyList_throwsException() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertThatThrownBy(() -> lotto.getNumbers().add(7))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("외부 리스트를 변경해도 Lotto 내부에는 영향이 없다.")
    @Test
    void modifyOriginalList_doesNotAffectLottoInternalState() {
        List<Integer> origin = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto = new Lotto(origin);

        origin.set(0, 99);

        assertThat(lotto.getNumbers())
                .containsExactly(1, 2, 3, 4, 5, 6);
    }
}

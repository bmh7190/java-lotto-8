package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import lotto.view.InputView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class InputViewTest {

    private final InputView inputView = new InputView();
    private InputStream backupIn;

    private void setInput(String input) {
        backupIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @AfterEach
    void tearDown() {
        if (backupIn != null) {
            System.setIn(backupIn);
        }

        Console.close();
    }

    @Nested
    @DisplayName("구입 금액 입력")
    class PurchaseAmountTest {

        @Test
        @DisplayName("정상적인 1000원 단위 금액을 입력하면 해당 금액을 반환한다")
        void inputPurchaseAmount_success() {

            setInput("8000\n");

            int amount = inputView.inputPurchaseAmount();

            assertThat(amount).isEqualTo(8000);
        }

        @Test
        @DisplayName("숫자가 아닌 값을 입력하면 예외가 발생한다")
        void inputPurchaseAmount_notNumber() {

            setInput("8천원\n");

            assertThatThrownBy(() -> inputView.inputPurchaseAmount())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("구입 금액은 숫자여야 합니다.");
        }

        @Test
        @DisplayName("0 이하의 금액을 입력하면 예외가 발생한다")
        void inputPurchaseAmount_notPositive() {

            setInput("0\n");

            assertThatThrownBy(() -> inputView.inputPurchaseAmount())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("구입 금액은 0보다 커야 합니다.");
        }

        @Test
        @DisplayName("1000원 단위가 아니면 예외가 발생한다")
        void inputPurchaseAmount_notThousandUnit() {

            setInput("1500\n");

            assertThatThrownBy(() -> inputView.inputPurchaseAmount())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("구입 금액은 1000원 단위여야 합니다.");
        }
    }

    @Nested
    @DisplayName("당첨 번호 입력")
    class WinningNumbersTest {

        @Test
        @DisplayName("올바른 6개 번호를 쉼표로 입력하면 리스트로 반환한다.")
        void inputWinningNumbers_success() {

            setInput("1,2,3,4,5,6\n");

            List<Integer> numbers = inputView.inputWinningNumbers();

            assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
        }

        @Test
        @DisplayName("1~45 범위를 벗어난 번호가 있으면 예외가 발생한다")
        void inputWinningNumbers_outOfRange() {

            setInput("1,2,3,4,5,46\n");

            assertThatThrownBy(() -> inputView.inputWinningNumbers())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("당첨 번호는 1에서 45 사이여야 합니다.");
        }

        @Test
        @DisplayName("숫자가 아닌 값이 포함되면 예외가 발생한다.")
        void inputWinningNumbers_notNumber() {

            setInput("1,우테코 화이팅,3,4,5,6");

            assertThatThrownBy(() -> inputView.inputWinningNumbers())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("당첨 번호는 숫자여야 합니다.");
        }

    }

    @Nested
    @DisplayName("보너스 번호 입력")
    class BonusNumberTest {

        private final List<Integer> winning = List.of(1, 2, 3, 4, 5, 6);

        @Test
        @DisplayName("보너스 번호를 정상 입력하면 값을 반환한다")
        void inputBonusNumber_success() {

            setInput("7\n");

            int bonus = inputView.inputBonusNumber(winning);

            assertThat(bonus).isEqualTo(7);
        }

        @Test
        @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외가 발생한다")
        void inputBonusNumber_outOfRange() {

            setInput("50\n");

            assertThatThrownBy(() -> inputView.inputBonusNumber(winning))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("보너스 번호는 1에서 45 사이여야 합니다.");
        }

        @Test
        @DisplayName("보너스 번호가 숫자가 아니면 예외가 발생한다")
        void inputBonusNumber_notNumber() {

            setInput("abc\n");

            assertThatThrownBy(() -> inputView.inputBonusNumber(winning))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("보너스 번호는 숫자여야 합니다.");
        }
    }



}

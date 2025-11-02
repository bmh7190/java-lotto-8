package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
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



}

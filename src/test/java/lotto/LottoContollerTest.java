package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.generator.FixLottoNumberGenerator;
import lotto.service.LottoService;
import lotto.view.TestOutputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoControllerTest {

    private static final List<Integer> WINNING = List.of(1, 2, 3, 4, 5, 6);
    private static final int BONUS = 7;

    private TestInputView inputView;
    private TestOutputView outputView;
    private LottoService lottoService;
    private LottoController controller;

    private void enqueueDefaultWinning() {
        inputView.enqueueWinningNumbers(WINNING);
        inputView.enqueueBonusNumber(BONUS);
    }

    @BeforeEach
    void setUp() {
        inputView = new TestInputView();
        outputView = new TestOutputView();
        lottoService = new LottoService(new FixLottoNumberGenerator(WINNING));
        controller = new LottoController(inputView, outputView, lottoService);
    }

    @DisplayName("모든 입력이 정상일 때 로또 구매부터 결과 출력까지 정상적으로 진행된다.")
    @Test
    void run_whenAllInputsValid() {
        inputView.enqueuePurchaseAmount(2000);
        enqueueDefaultWinning();

        controller.run();

        assertThat(outputView.errors).isEmpty();
        assertThat(outputView.printedLottos).hasSize(2);
        assertThat(outputView.printedWinningCounts).isNotNull();
        assertThat(outputView.printedProfitRate).isNotNull();
    }

    @DisplayName("구매 금액 입력이 한 번 실패해도 다시 입력받아 정상 흐름으로 진행한다.")
    @Test
    void run_retryPurchaseAmount_whenFirstInputInvalid() {
        inputView.enqueuePurchaseAmountError(
                new IllegalArgumentException("[ERROR] 구입 금액은 0보다 커야 합니다.")
        );
        inputView.enqueuePurchaseAmount(2000);
        enqueueDefaultWinning();

        controller.run();

        assertThat(outputView.errors)
                .containsExactly("[ERROR] 구입 금액은 0보다 커야 합니다.");
        assertThat(outputView.printedLottos).hasSize(2);
        assertThat(outputView.printedWinningCounts).isNotNull();
        assertThat(outputView.printedProfitRate).isNotNull();
    }

    @DisplayName("구입 금액이 1000원 단위가 아니면 다시 입력받아 정상 흐름으로 진행한다.")
    @Test
    void run_retryPurchaseAmount_whenNotThousandUnit() {
        inputView.enqueuePurchaseAmountError(
                new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.")
        );
        inputView.enqueuePurchaseAmount(2000);
        enqueueDefaultWinning();

        controller.run();

        assertThat(outputView.errors)
                .containsExactly("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        assertThat(outputView.printedLottos).hasSize(2);
        assertThat(outputView.printedWinningCounts).isNotNull();
        assertThat(outputView.printedProfitRate).isNotNull();
    }

    @DisplayName("당첨 번호 입력이 한 번 실패해도 다시 입력받아 정상 흐름으로 진행한다.")
    @Test
    void run_retryWinningNumbers_whenFirstInputInvalid() {
        inputView.enqueuePurchaseAmount(2000);

        inputView.enqueueWinningNumbersError(
                new IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.")
        );
        enqueueDefaultWinning();

        controller.run();

        assertThat(outputView.errors)
                .containsExactly("[ERROR] 당첨 번호는 숫자여야 합니다.");
        assertThat(outputView.printedLottos).hasSize(2);
        assertThat(outputView.printedWinningCounts).isNotNull();
        assertThat(outputView.printedProfitRate).isNotNull();
    }

    @DisplayName("보너스 번호 입력이 한 번 실패해도 다시 입력받아 정상 흐름으로 진행한다.")
    @Test
    void run_retryBonusNumber_whenFirstInputInvalid() {
        inputView.enqueuePurchaseAmount(2000);
        inputView.enqueueWinningNumbers(WINNING);

        inputView.enqueueBonusNumberError(
                new IllegalArgumentException("[ERROR] 보너스 번호는 1에서 45 사이여야 합니다.")
        );
        inputView.enqueueBonusNumber(BONUS);

        controller.run();

        assertThat(outputView.errors)
                .containsExactly("[ERROR] 보너스 번호는 1에서 45 사이여야 합니다.");
        assertThat(outputView.printedLottos).hasSize(2);
        assertThat(outputView.printedWinningCounts).isNotNull();
        assertThat(outputView.printedProfitRate).isNotNull();
    }

    @DisplayName("모든 입력이 한 번씩 실패해도 재입력 후 정상 흐름으로 진행된다.")
    @Test
    void run_allInputsRetryAndThenSuccess() {
        // 구매금액 실패 → 성공
        inputView.enqueuePurchaseAmountError(
                new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.")
        );
        inputView.enqueuePurchaseAmount(2000);

        // 당첨번호 실패 → 성공
        inputView.enqueueWinningNumbersError(
                new IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.")
        );
        inputView.enqueueWinningNumbers(WINNING);

        // 보너스번호 실패 → 성공
        inputView.enqueueBonusNumberError(
                new IllegalArgumentException("[ERROR] 보너스 번호는 1에서 45 사이여야 합니다.")
        );
        inputView.enqueueBonusNumber(BONUS);

        controller.run();

        assertThat(outputView.errors).containsExactly(
                "[ERROR] 구입 금액은 1000원 단위여야 합니다.",
                "[ERROR] 당첨 번호는 숫자여야 합니다.",
                "[ERROR] 보너스 번호는 1에서 45 사이여야 합니다."
        );
        assertThat(outputView.printedLottos).hasSize(2);
        assertThat(outputView.printedWinningCounts).isNotNull();
        assertThat(outputView.printedProfitRate).isNotNull();
    }
}

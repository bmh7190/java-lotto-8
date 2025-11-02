package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lotto.view.OutputView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OutputViewTest {

    private final OutputView outputView = new OutputView();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @BeforeEach
    void setUpStreams() {
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("구매한 로또 목록을 개수와 함께 출력한다")
    void printPurchasedLottos_shouldPrintSortedLists() {

        List<List<Integer>> lottos = List.of(
                List.of(3, 1, 5, 2, 4, 6),
                List.of(10, 8, 9, 7, 12, 11)
        );

        outputView.printPurchasedLottos(lottos);

        String output = outContent.toString().trim();

        assertThat(output).contains("2개를 구매했습니다.");
        assertThat(output).contains("[1, 2, 3, 4, 5, 6]");
        assertThat(output).contains("[7, 8, 9, 10, 11, 12]");
    }

    @Test
    @DisplayName("당첨 통계를 지정된 형식으로 정확히 출력한다")
    void printWinningStatistics_shouldPrintExactFormat() {

        Map<String, Integer> results = new LinkedHashMap<>();
        results.put("3개 일치 (5,000원)", 1);
        results.put("4개 일치 (50,000원)", 0);
        results.put("5개 일치 (1,500,000원)", 0);
        results.put("5개 일치, 보너스 볼 일치 (30,000,000원)", 0);
        results.put("6개 일치 (2,000,000,000원)", 0);

        outputView.printWinningStatistics(results);

        String expectedOutput = String.join(System.lineSeparator(),
                "당첨 통계",
                "---",
                "3개 일치 (5,000원) - 1개",
                "4개 일치 (50,000원) - 0개",
                "5개 일치 (1,500,000원) - 0개",
                "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                "6개 일치 (2,000,000,000원) - 0개"
        );

        String actualOutput = outContent.toString().trim();

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("수익률을 소수점 첫째 자리까지 반올림하여 출력한다")
    void printProfitRate_shouldRoundToOneDecimalPlace() {

        double profitRate = 62.6666;

        outputView.printProfitRate(profitRate);

        String output = outContent.toString().trim();

        assertThat(output).contains("총 수익률은 62.7%");
    }

}

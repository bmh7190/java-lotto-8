package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
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

}

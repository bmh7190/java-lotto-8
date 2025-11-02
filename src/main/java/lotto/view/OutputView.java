package lotto.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printPurchasedLottos(List<List<Integer>> lottos) {
        System.out.printf("%d개를 구매했습니다.%n", lottos.size());
        for (List<Integer> lotto : lottos) {
            List<Integer> sorted = new ArrayList<>(lotto);
            Collections.sort(sorted);
            System.out.println(sorted);
        }
    }

    public void printWinningStatistics(Map<String, Integer> results) {
        System.out.println("당첨 통계");
        System.out.println("---");

        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue() + "개");
        }
    }

}

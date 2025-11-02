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

    public void printWinningStatistics(List<Integer> counts) {
        System.out.println("당첨 통계");
        System.out.println("---");

        System.out.printf("3개 일치 (5,000원) - %d개%n", counts.get(0));
        System.out.printf("4개 일치 (50,000원) - %d개%n", counts.get(1));
        System.out.printf("5개 일치 (1,500,000원) - %d개%n", counts.get(2));
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개%n", counts.get(3));
        System.out.printf("6개 일치 (2,000,000,000원) - %d개%n", counts.get(4));
    }

    public void printProfitRate(double profitRate) {
        double rounded = Math.round(profitRate * 10) / 10.0;
        System.out.printf("총 수익률은 %,.1f%%입니다.%n", rounded);
    }

    public void printError(String message) {
        System.out.println(message);
    }
}

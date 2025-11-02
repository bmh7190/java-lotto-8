package lotto.support;

import java.util.ArrayList;
import java.util.List;
import lotto.view.OutputView;

public class TestOutputView extends OutputView {

    public final List<String> errors = new ArrayList<>();
    public final List<List<Integer>> printedLottos = new ArrayList<>();
    public List<Integer> printedWinningCounts;
    public Double printedProfitRate;

    @Override
    public void printPurchasedLottos(List<List<Integer>> lottos) {
        printedLottos.addAll(lottos);
    }

    @Override
    public void printWinningStatistics(List<Integer> counts) {
        this.printedWinningCounts = counts;
    }

    @Override
    public void printProfitRate(double profitRate) {
        this.printedProfitRate = profitRate;
    }

    @Override
    public void printError(String message) {
        errors.add(message);
    }
}


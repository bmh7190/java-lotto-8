package lotto.generator;

import static camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange;

import java.util.List;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {

    private static final int START = 1;
    private static final int END = 45;
    private static final int COUNT = 6;

    @Override
    public List<Integer> generateNumbers() {
        return pickUniqueNumbersInRange(START, END, COUNT);
    }
}

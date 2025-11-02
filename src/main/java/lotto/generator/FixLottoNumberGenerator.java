package lotto.generator;

import java.util.List;

public class FixLottoNumberGenerator implements LottoNumberGenerator {

    private final List<Integer> numbers;

    public FixLottoNumberGenerator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public List<Integer> generateNumbers() {
        return numbers;
    }

}

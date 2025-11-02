package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.common.ErrorMessage;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        List<Integer> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);
        this.numbers = Collections.unmodifiableList(sorted);
    }

    private void validate(List<Integer> numbers) {

        if (numbers == null) {
            throw new IllegalArgumentException(ErrorMessage.of("[ERROR] 로또 번호가 비어있습니다."));
        }

        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.of("[ERROR] 로또 번호는 6개여야 합니다."));
        }

        Set<Integer> unique = new HashSet<>(numbers);
        if (unique.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.of("[ERROR] 로또 번호는 중복될 수 없습니다."));
        }
    }


    public List<Integer> getNumbers() {
        return numbers;
    }

}

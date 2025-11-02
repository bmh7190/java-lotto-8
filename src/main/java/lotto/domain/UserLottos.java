package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserLottos {

    private final List<Lotto> lottos;

    public UserLottos(List<Lotto> lottos) {
        this.lottos = Collections.unmodifiableList(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int size() {
        return lottos.size();
    }

    public List<List<Integer>> getLottoNumbers() {
        return lottos.stream()
                .map(Lotto::getNumbers)
                .collect(Collectors.toList());
    }

}

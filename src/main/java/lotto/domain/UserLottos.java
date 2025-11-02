package lotto.domain;

import java.util.Collections;
import java.util.List;

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

}

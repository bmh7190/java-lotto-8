package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {

    private final Map<Rank, Integer> counts = new EnumMap<>(Rank.class);

    public LottoResult(Map<Rank, Integer> result) {
        for (Rank rank : Rank.values()) {
            counts.put(rank, result.getOrDefault(rank, 0));
        }
    }

    public int getCount(Rank rank) {
        return counts.getOrDefault(rank, 0);
    }

    public Map<Rank, Integer> getCounts() {
        return counts;
    }

    public long getTotalPrize() {
        return counts.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrize() * entry.getValue())
                .sum();
    }
}

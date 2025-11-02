package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.common.ErrorMessage;

public class InputView {

    public int inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();

        int amount = parseToInt(input);

        if (amount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.of("구입 금액은 1000원 단위여야 합니다."));
        }

        if (amount % 1000 != 0) {
            throw new IllegalArgumentException(ErrorMessage.of("1000원 단위로 입력해주세요."));
        }

        return amount;
    }

    public List<Integer> inputWinningNumbers() {
        System.out.println("당첨번호를 입력해 주세요.");
        String input = Console.readLine();

        String[] tokens = input.split(",");

        if (tokens.length != 6) {
            throw new IllegalArgumentException(ErrorMessage.of("당첨 번호는 6개여야 합니다."));
        }

        List<Integer> numbers = new ArrayList<>(6);
        Set<Integer> duplicates = new HashSet<>();

        for (String token : tokens) {
            String trimmed = token.trim();

            int number = parseToInt(trimmed);

            if (number < 1 || number > 45) {
                throw new IllegalArgumentException(ErrorMessage.of("당첨 번호는 1에서 45 사이여야 합니다."));
            }

            if (!duplicates.add(number)) {
                throw new IllegalArgumentException(ErrorMessage.of("당첨 번호는 중복될 수 없습니다."));
            }

            numbers.add(number);
        }

        return numbers;

    }

    private int parseToInt(String input) {
        if (!isNumeric(input)) {
            throw new IllegalArgumentException(ErrorMessage.of("구입 금액은 숫자여야 합니다."));
        }
        return Integer.parseInt(input);
    }

    private boolean isNumeric(String input) {
        return input.matches("\\d+");
    }
}

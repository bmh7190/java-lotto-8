package lotto.view;

import camp.nextstep.edu.missionutils.Console;
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

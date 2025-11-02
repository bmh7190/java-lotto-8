package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.common.ErrorMessage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputView {

    private static final int LOTTO_PRICE = 1000;
    private static final int WINNING_NUMBER_COUNT = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    public int inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();

        int amount = parseToInt(input, "구입 금액은 숫자여야 합니다.");

        validatePurchaseAmount(amount);

        return amount;
    }

    public List<Integer> inputWinningNumbers() {
        System.out.println("당첨번호를 입력해 주세요.");
        String input = Console.readLine();

        List<Integer> numbers = parseWinningNumbers(input);

        validateWinningNumbers(numbers);

        return numbers;
    }

    public int inputBonusNumber(List<Integer> winningNumbers) {
        System.out.println("보너스 번호를 입력해 주세요.");
        String input = Console.readLine();

        int bonus = parseToInt(input, "보너스 번호는 숫자여야 합니다.");

        validateBonusNumber(bonus, winningNumbers);

        return bonus;
    }

    private List<Integer> parseWinningNumbers(String input) {
        String[] tokens = input.split(",");
        List<Integer> numbers = new ArrayList<>();

        for (String token : tokens) {
            String trimmed = token.trim();
            numbers.add(parseToInt(trimmed, "당첨 번호는 숫자여야 합니다."));
        }

        return numbers;
    }

    private int parseToInt(String input, String errorMessage) {
        if (!isNumeric(input)) {
            throw new IllegalArgumentException(ErrorMessage.of(errorMessage));
        }
        return Integer.parseInt(input);
    }

    private boolean isNumeric(String input) {
        return input.matches("\\d+");
    }

    private void validatePurchaseAmount(int amount) {
        validatePositive(amount);
        validateThousandUnit(amount);
    }

    private void validatePositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.of("구입 금액은 0보다 커야 합니다."));
        }
    }

    private void validateThousandUnit(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.of("구입 금액은 1000원 단위여야 합니다."));
        }
    }

    private void validateWinningNumbers(List<Integer> numbers) {
        validateCount(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
    }

    private void validateCount(List<Integer> numbers) {
        if (numbers.size() != WINNING_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.of("당첨 번호는 6개여야 합니다."));
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new IllegalArgumentException(ErrorMessage.of("당첨 번호는 1에서 45 사이여야 합니다."));
            }
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> unique = new HashSet<>(numbers);
        if (unique.size() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.of("당첨 번호는 중복될 수 없습니다."));
        }
    }

    private void validateBonusNumber(int bonus, List<Integer> winningNumbers) {
        validateBonusRange(bonus);
        validateBonusDuplicate(bonus, winningNumbers);
    }

    private void validateBonusRange(int bonus) {
        if (bonus < MIN_NUMBER || bonus > MAX_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.of("보너스 번호는 1에서 45 사이여야 합니다."));
        }
    }

    private void validateBonusDuplicate(int bonus, List<Integer> winningNumbers) {
        if (winningNumbers.contains(bonus)) {
            throw new IllegalArgumentException(ErrorMessage.of("보너스 번호는 당첨 번호와 중복될 수 없습니다."));
        }
    }
}

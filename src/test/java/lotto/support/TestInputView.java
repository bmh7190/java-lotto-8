package lotto.support;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import lotto.view.InputView;

public class TestInputView extends InputView {

    private final Deque<Object> purchaseAmountQueue = new ArrayDeque<>();
    private final Deque<Object> winningNumbersQueue = new ArrayDeque<>();
    private final Deque<Object> bonusNumberQueue = new ArrayDeque<>();

    //구매금액
    public void enqueuePurchaseAmountError(RuntimeException e) {
        purchaseAmountQueue.addLast(e);
    }

    public void enqueuePurchaseAmount(int amount) {
        purchaseAmountQueue.addLast(amount);
    }

    //당첨번호
    public void enqueueWinningNumbersError(RuntimeException e) {
        winningNumbersQueue.addLast(e);
    }

    public void enqueueWinningNumbers(List<Integer> numbers) {
        winningNumbersQueue.addLast(numbers);
    }

    //보너스번호
    public void enqueueBonusNumberError(RuntimeException e) {
        bonusNumberQueue.addLast(e);
    }

    public void enqueueBonusNumber(int bonus) {
        bonusNumberQueue.addLast(bonus);
    }

    @Override
    public int inputPurchaseAmount() {
        Object next = pollOrFail(purchaseAmountQueue, "purchaseAmount");
        if (next instanceof RuntimeException e) {
            throw e;
        }
        return (int) next;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Integer> inputWinningNumbers() {
        Object next = pollOrFail(winningNumbersQueue, "winningNumbers");
        if (next instanceof RuntimeException e) {
            throw e;
        }
        return (List<Integer>) next;
    }

    @Override
    public int inputBonusNumber(List<Integer> winningNumbers) {
        Object next = pollOrFail(bonusNumberQueue, "bonusNumber");
        if (next instanceof RuntimeException e) {
            throw e;
        }
        return (int) next;
    }

    private <T> T pollOrFail(Deque<T> deque, String name) {
        T value = deque.pollFirst();
        if (value == null) {
            throw new IllegalStateException("no more test input for " + name);
        }
        return value;
    }
}

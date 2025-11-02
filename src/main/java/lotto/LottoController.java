package lotto;

import java.util.List;
import lotto.domain.LottoResult;
import lotto.domain.UserLottos;
import lotto.domain.WinningLotto;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        int purchaseAmount = requestPurchaseAmount();

        UserLottos userLottos = issueLottos(purchaseAmount);
        WinningLotto winningLotto = inputWinningLotto();
        LottoResult lottoResult = evaluateResult(userLottos, winningLotto);

        printResult(lottoResult, purchaseAmount);
    }

    private int requestPurchaseAmount() {
        return inputView.inputPurchaseAmount();
    }

    private UserLottos issueLottos(int purchaseAmount) {
        UserLottos userLottos = lottoService.createUserLottos(purchaseAmount);
        outputView.printPurchasedLottos(userLottos.getLottoNumbers());
        return userLottos;
    }

    private WinningLotto inputWinningLotto() {
        List<Integer> winningNumbers = inputView.inputWinningNumbers();
        int bonusNumber = inputView.inputBonusNumber(winningNumbers);
        return lottoService.createWinningLotto(winningNumbers, bonusNumber);
    }

    private LottoResult evaluateResult(UserLottos userLottos, WinningLotto winningLotto) {
        return lottoService.calculateResult(userLottos, winningLotto);
    }

    private void printResult(LottoResult lottoResult, int purchaseAmount) {
        outputView.printWinningStatistics(lottoResult.getWinningCounts());
        double profitRate = lottoService.calculateYield(lottoResult, purchaseAmount);
        outputView.printProfitRate(profitRate);
    }
}

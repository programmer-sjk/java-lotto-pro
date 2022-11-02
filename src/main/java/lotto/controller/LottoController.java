package lotto.controller;

import lotto.domain.*;
import lotto.domain.ManualLottoGenerator;
import lotto.domain.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    public void run() {
        Money money = new Money(InputView.getMoney());
        Quantity manualQuantity = new Quantity(InputView.getManualLottoCount());
        InputView.getManualNumbers(manualQuantity);

        List<LottoTicket> tickets = LottoMarket.sell(
                money,
                new RandomLottoGenerator()
        );

        OutputView.printTickets(tickets, manualQuantity);
        showLottoResult(money, tickets);
    }

    private void showLottoResult(Money money, List<LottoTicket> tickets) {
        LottoTicket winningTicket = new ManualLottoGenerator(createWinningNumbers()).create();
        LottoResult lottoResult = new LottoResult(winningTicket);

        OutputView.printStatistics(lottoResult.statistics(tickets, chooseBonusNumber(winningTicket)));
        OutputView.printReturnRate(lottoResult.returnRate(money));
    }

    private List<Integer> createWinningNumbers() {
        return ManualLottoGenerator.toNumbers(InputView.getWiningNumber());
    }

    private LottoNumber chooseBonusNumber(LottoTicket winningTicket) {
        LottoNumber number = LottoNumber.get(InputView.getBonusNumber());
        if (winningTicket.contain(number)) {
            OutputView.printDuplicateNumber();
            return chooseBonusNumber(winningTicket);
        }

        return number;
    }
}

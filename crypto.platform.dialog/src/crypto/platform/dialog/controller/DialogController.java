package crypto.platform.dialog.controller;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import crypto.model.Coin;
import crypto.model.CoinList;
import crypto.platform.dialog.DialogActivator;
import crypto.platform.service.Service;
import crypto.platform.ui.api.controller.ComponentController;

public class DialogController extends ComponentController {

  private static Service service = DialogActivator.getService();

  private Composite composite;
  private Text inputField;
  private Button add;
  private CoinList coinList;

  public DialogController(Composite parent, Text inputField, Button add) {
    super(parent);
    this.composite = parent;
    this.inputField = inputField;
    this.add = add;
    coinList = service.getCoinList();

    add.addListener(
        SWT.Selection,
        e -> {
          addCoin();
        });
  }

  public void addInputFieldListener(Listener listener) {
    inputField.addListener(SWT.Modify, listener);
  }

  public void addCoin() {
    String symbol = inputField.getText();
    Coin addCoin = coinList.findCoinByName(symbol);
    // If text box is not empty, an identical coin is not already added,
    // and the coin exists in the API database
    if (!symbol.isEmpty()
        && addCoin != null
        && (service.getAll().indexOf(addCoin) == -1)) {
      service.addCoin(addCoin);
      inputField.setText("");
    }
  }

  public String getText() {
    return inputField.getText();
  }

  public void validateInput(Button button) {
    if (coinList.getSymbolList().contains(inputField.getText())){
      button.setEnabled(true);
      add.setEnabled(true);
    }
    else{
      button.setEnabled(false);
      add.setEnabled(false);
    }
  }
}

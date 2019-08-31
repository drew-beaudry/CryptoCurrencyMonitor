package crypto.platform.view.coinlist.provider;

import org.eclipse.jface.viewers.LabelProvider;

import crypto.model.Coin;

public class ListLabelProvider extends LabelProvider {

  //Provides label content for ListView
  @Override
  public String getText(Object element) {
    if (element instanceof Coin)
      return ((Coin) element).getName();
    else
      return element.toString();
  }
  
}

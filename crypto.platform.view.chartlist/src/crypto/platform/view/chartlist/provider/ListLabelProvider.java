package crypto.platform.view.chartlist.provider;

import org.eclipse.jface.viewers.LabelProvider;

import crypto.model.Chart;

public class ListLabelProvider extends LabelProvider {

  //Provides label content for ListView
  @Override
  public String getText(Object element) {
    if (element instanceof Chart)
      return ((Chart) element).getName();
    else
      return element.toString();
  }
  
}

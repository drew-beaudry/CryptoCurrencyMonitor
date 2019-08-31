package crypto.platform.view.coinlist.component;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import crypto.platform.ui.api.component.IComponent;
import crypto.platform.view.coinlist.controller.CoinListController;
import crypto.platform.view.coinlist.handler.DoubleClickListener;
import crypto.platform.view.coinlist.provider.ListLabelProvider;

// Manage creation
// - Set providers

public class CoinListComponent implements IComponent {

  @Override
  public CoinListController create(Composite composite) {
    composite.setLayout(new GridLayout(1, false));

    Button add = new Button(composite, SWT.PUSH);
    ListViewer viewer = new ListViewer(composite);
    add.setText("Add");
    add.setLayoutData(new GridData(SWT.RIGHT, SWT.NONE, true, false, 1, 1));
    viewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2));
    viewer.setContentProvider(ArrayContentProvider.getInstance());

    viewer.setLabelProvider(new ListLabelProvider());
    viewer.addDoubleClickListener(new DoubleClickListener());

    return new CoinListController(composite, viewer, add);
  }
}

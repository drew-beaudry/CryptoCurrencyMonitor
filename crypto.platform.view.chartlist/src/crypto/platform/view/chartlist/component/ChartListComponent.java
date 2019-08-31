package crypto.platform.view.chartlist.component;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import crypto.platform.ui.api.component.IComponent;
import crypto.platform.view.chartlist.controller.ChartListController;
import crypto.platform.view.chartlist.handler.DoubleClickListener;
import crypto.platform.view.chartlist.provider.ListLabelProvider;

public class ChartListComponent implements IComponent{
  private ListViewer viewer;
  
  @Override
  public ChartListController create(Composite composite) {
    composite.setLayout(new GridLayout(1,false));
    
    viewer = new ListViewer(composite);
    viewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2));
    viewer.setContentProvider(ArrayContentProvider.getInstance());
    viewer.setLabelProvider(new ListLabelProvider());
    viewer.addDoubleClickListener(new DoubleClickListener());
    return new ChartListController(composite, viewer);
  }}

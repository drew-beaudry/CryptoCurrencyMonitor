package crypto.platform.view.table.component;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import crypto.platform.ui.api.component.IComponent;
import crypto.platform.view.table.controller.TableController;
import crypto.platform.view.table.provider.TableContentProvider;
import crypto.platform.view.table.provider.TableLabelProvider;

public class TableComponent implements IComponent {
  // TableViewer viewer;

  @Override
  public TableController create(Composite container) {
    container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    GridLayout layout = new GridLayout(1, false);
    container.setLayout(layout);
    Button alignment = new Button(container, SWT.NONE);
    alignment.setLayoutData(new GridData(SWT.RIGHT, SWT.NONE, false, false, 1, 1));
    alignment.setVisible(false);
    // define the TableViewer
    TableViewer viewer =
        new TableViewer(
            container, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
    viewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2));
    createColumns(viewer);

    viewer.setContentProvider(new TableContentProvider());
    viewer.setLabelProvider(new TableLabelProvider());

    return new TableController(container, viewer);
  }

  public void createColumns(TableViewer viewer) {
    viewer.getTable().setHeaderVisible(true);
    viewer.getTable().setLinesVisible(true);

    TableViewerColumn currency = new TableViewerColumn(viewer, SWT.NONE);
    currency.getColumn().setWidth(210);
    currency.getColumn().setText("Currency");

    TableViewerColumn price = new TableViewerColumn(viewer, SWT.NONE);
    price.getColumn().setWidth(210);
    price.getColumn().setText("Price (USD)");

    TableViewerColumn status = new TableViewerColumn(viewer, SWT.CENTER);
    status.getColumn().setWidth(51);
    status.getColumn().setText("Status");

    Image statusImage = status.getColumn().getImage();
    Table table = viewer.getTable();

    Listener paintListener =
        event -> {
          if (statusImage != null) {
            switch (event.type) {
              case SWT.MeasureItem:
                {
                  Rectangle rect1 = statusImage.getBounds();
                  event.width += rect1.width;
                  event.height = Math.max(event.height, rect1.height + 2);
                  break;
                }
              case SWT.PaintItem:
                {
                  TableItem item = (TableItem) event.item;
                  Rectangle rect2 = statusImage.getBounds();
                  Rectangle bounds = item.getBounds(event.index);
                  int x = bounds.x + bounds.width - rect2.width;
                  int offset = Math.max(0, (event.height - rect2.height) / 2);
                  event.gc.drawImage(statusImage, x, event.y + offset);
                  break;
                }
            }
          }
        };
    table.addListener(SWT.MeasureItem, paintListener);
    table.addListener(SWT.PaintItem, paintListener);
  }
}

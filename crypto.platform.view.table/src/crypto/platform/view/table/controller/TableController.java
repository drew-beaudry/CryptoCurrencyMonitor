package crypto.platform.view.table.controller;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import crypto.platform.service.Service;
import crypto.platform.ui.api.controller.ComponentController;
import crypto.platform.view.table.Activator;

public class TableController extends ComponentController {

	private TableViewer viewer;
	private final Table table;
	private static Service service = Activator.getService();

	private static final Logger log = Logger.getLogger(TableController.class);

	public TableController(Composite parent, TableViewer viewer) {
		super(parent);
		this.viewer = viewer;
		this.table = viewer.getTable();
	}

	public void setInput(Object input) {
		viewer.setInput(input);
		viewer.refresh();
		// log.info("Timer Created");

	}

	public void refresh() {
		viewer.refresh();
	}

	public void dispose() {

	}
}

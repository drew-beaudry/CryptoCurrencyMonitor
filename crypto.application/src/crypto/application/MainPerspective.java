package crypto.application;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/** Main perspective */
public class MainPerspective implements IPerspectiveFactory {
	
	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.setFixed(true);
		layout.setEditorAreaVisible(false);
	}
}

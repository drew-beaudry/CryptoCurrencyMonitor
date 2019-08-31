package crypto.application;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/** Perspective used for live pricing data */
public class LivePerspective implements IPerspectiveFactory {

  @Override
  public void createInitialLayout(IPageLayout layout) {
    layout.setFixed(true);
    layout.setEditorAreaVisible(false);
  }
}

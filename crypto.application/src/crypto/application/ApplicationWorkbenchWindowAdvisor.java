package crypto.application;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import crypto.api.cryptocompare.constants.Constants;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

  public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
    super(configurer);
  }

  public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
    return new ApplicationActionBarAdvisor(configurer);
  }

  public void preWindowOpen() {
    IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
    // Set size of application
    configurer.setInitialSize(Constants.APPLICATIONSIZE);
    configurer.setShowCoolBar(true);
    configurer.setShowStatusLine(false);
    configurer.setTitle(Constants.APPLICATIONNAME); // $NON-NLS-1$
    configurer.setShowPerspectiveBar(true);

    IPreferenceStore perStore = PlatformUI.getPreferenceStore();

    //perStore.setValue(IWorkbenchPreferenceConstants.DOCK_PERSPECTIVE_BAR, "RIGHT");
    perStore.setValue(IWorkbenchPreferenceConstants.SHOW_TEXT_ON_PERSPECTIVE_BAR, true);
    perStore.setValue(IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS, true);
    perStore.setValue(IWorkbenchPreferenceConstants.INITIAL_FAST_VIEW_BAR_LOCATION, true);
  }
}

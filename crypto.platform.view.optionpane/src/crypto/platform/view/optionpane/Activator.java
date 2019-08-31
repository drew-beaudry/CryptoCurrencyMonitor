package crypto.platform.view.optionpane;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import crypto.platform.service.Service;

/** The activator class controls the plug-in life cycle */
public class Activator extends AbstractUIPlugin {
  private static Service service;
  // The plug-in ID
  public static final String PLUGIN_ID = "crypto.platform.view.optionpane"; // $NON-NLS-1$

  // The shared instance
  private static Activator plugin;

  /** The constructor */
  public Activator() {}

  /*
   * (non-Javadoc)
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
    ServiceReference<?> serviceReference = context.getServiceReference(Service.class.getName());
    service = (Service) context.getService(serviceReference);
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  public static Service getService() {
    return service;
  }

  /**
   * Returns the shared instance
   *
   * @return the shared instance
   */
  public static Activator getDefault() {
    return plugin;
  }
}

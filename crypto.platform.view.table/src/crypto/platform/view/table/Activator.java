package crypto.platform.view.table;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import crypto.platform.service.Service;

/** The activator class controls the plug-in life cycle */
public class Activator extends AbstractUIPlugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "crypto.platform.view.table"; // $NON-NLS-1$

  public static final String UP_ICON = "UP";

  public static final String DOWN_ICON = "DOWN";

  // The shared instance
  private static Activator plugin;
  private static Service service;

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

  /**
   * Returns the shared instance
   *
   * @return the shared instance
   */
  public static Activator getDefault() {
    return plugin;
  }

  public static Service getService() {
    return service;
  }
  /**
   * Returns an image descriptor for the image file at the given plug-in
   * relative path
   *
   * @param path
   *            the path
   * @return the image descriptor
   */
   public static ImageDescriptor getImageDescriptor(String path) {
         return imageDescriptorFromPlugin(PLUGIN_ID, path);
   }
   
   @Override
   protected void initializeImageRegistry(ImageRegistry reg) {
         reg.put(UP_ICON, getImageDescriptor("icons/up.ico"));
         reg.put(DOWN_ICON, getImageDescriptor("icons/down.ico"));
   }


}

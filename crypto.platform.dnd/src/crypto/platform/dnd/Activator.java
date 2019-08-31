package crypto.platform.dnd;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import crypto.platform.service.Service;


public class Activator implements BundleActivator {
  private static Service service;
  private static BundleContext context;

  static BundleContext getContext() {
    return context;
  }

  /*
   * (non-Javadoc)
   * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
   */
  public void start(BundleContext bundleContext) throws Exception {
    Activator.context = bundleContext;
    ServiceReference<?> serviceReference = context.getServiceReference(Service.class.getName());
    service = (Service) context.getService(serviceReference);
  }

  public static Service getService() {
    return service;
  }
  /*
   * (non-Javadoc)
   * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
   */
  public void stop(BundleContext bundleContext) throws Exception {
    Activator.context = null;
  }
}

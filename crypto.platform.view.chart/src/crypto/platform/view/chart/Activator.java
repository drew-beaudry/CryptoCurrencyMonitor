package crypto.platform.view.chart;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import crypto.platform.service.Service;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private static Service service;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
    ServiceReference<?> serviceReference = context.
        getServiceReference(Service.class.getName());
    service = (Service) context.getService(serviceReference);
  }
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
	  
	  static public Service getService(){
	    return service;
	  }

}

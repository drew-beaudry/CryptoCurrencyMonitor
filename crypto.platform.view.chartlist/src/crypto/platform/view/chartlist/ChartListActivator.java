package crypto.platform.view.chartlist;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import crypto.platform.service.Service;

/**
 * The activator class controls the plug-in life cycle
 */
public class ChartListActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "crypto.platform.view.chartlist"; //$NON-NLS-1$

	// The shared instance
	private static ChartListActivator plugin;
	
	private static Service service;
	
	/**
	 * The constructor
	 */
	public ChartListActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
    ServiceReference<?> serviceReference = context.
        getServiceReference(Service.class.getName());
    service = (Service) context.getService(serviceReference);
	}
	
	static public Service getService(){
	  return service;
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
	public static ChartListActivator getDefault() {
		return plugin;
	}

}

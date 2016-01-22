package net.caseydunham.seed.stripes.ext.guice.interceptor;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.config.BootstrapPropertyResolver;
import net.sourceforge.stripes.config.ConfigurableComponent;
import net.sourceforge.stripes.config.Configuration;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Intercepts(LifecycleStage.ActionBeanResolution)
public class GuiceInterceptor implements Interceptor, ConfigurableComponent {

	private static final Logger LOG = LoggerFactory.getLogger(GuiceInterceptor.class);

	public static final String MODULES = "Guice.Modules";

	private static Injector injector;

	public void init(Configuration configuration) throws Exception {
		LOG.info("configuring guice interceptor");
		BootstrapPropertyResolver resolver = configuration.getBootstrapPropertyResolver();
		List<Class<? extends Module>> moduleClasses = resolver.getClassPropertyList(MODULES, Module.class);
		int size = moduleClasses.size();
		if (size > 0) {
			List<Module> modules = new ArrayList<Module>(size);
			for (Class<? extends Module> cls : moduleClasses) {
				modules.add(cls.newInstance());
			}
			injector = Guice.createInjector(modules);
		} else {
			injector = Guice.createInjector();
		}
	}

	public Resolution intercept(ExecutionContext context) throws Exception {
		getInjector().injectMembers(context.getActionBeanContext());
		Resolution resolution = context.proceed();
		getInjector().injectMembers(context.getActionBean());
		return resolution;
	}

	public static Injector getInjector() { return injector; }

}

package net.caseydunham.seed.stripes.interceptor;

import net.caseydunham.seed.hibernate.HibernateUtil;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.config.ConfigurableComponent;
import net.sourceforge.stripes.config.Configuration;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;

@Intercepts(value = {LifecycleStage.HandlerResolution, LifecycleStage.ResolutionExecution})
public class HibernateInterceptor implements Interceptor, ConfigurableComponent {

	private static final Logger LOG = LoggerFactory.getLogger(HibernateInterceptor.class);

	public void init(Configuration configuration) throws Exception {
		LOG.debug("initialized");
	}

	@SuppressWarnings("unchecked")
	public Resolution intercept(ExecutionContext ctx) throws Exception {
		Resolution next;
		try {
			if (ctx.getLifecycleStage().equals(LifecycleStage.HandlerResolution)) {
				LOG.debug("beginning hibernate transaction");
				HibernateUtil.beginTransaction();
			}
			next = ctx.proceed();
			if (ctx.getLifecycleStage().equals(LifecycleStage.ResolutionExecution)) {
				LOG.debug("committing and closing transaction");
				HibernateUtil.commit();
			}
		} catch (Throwable ex) {
			HibernateUtil.rollback();
			if (ex instanceof ServletException) {
				throw (ServletException) ex;
			} else if (ex instanceof HibernateException) {
				throw (HibernateException) ex;
			} else {
				throw new ServletException(ex);
			}
		}
		return next;
	}

}
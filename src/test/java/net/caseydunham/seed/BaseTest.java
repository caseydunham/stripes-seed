package net.caseydunham.seed;

import net.caseydunham.seed.hibernate.HibernateUtil;
import net.sourceforge.stripes.controller.DispatcherServlet;
import net.sourceforge.stripes.controller.StripesFilter;
import net.sourceforge.stripes.mock.MockHttpSession;
import net.sourceforge.stripes.mock.MockServletContext;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {

	private static Configuration configuration;
	private static SessionFactory sessionFactory;

	protected static MockServletContext mockServletContext;
	protected static MockHttpSession mockHttpSession;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);

		configuration = new Configuration();
		configuration.configure();
		sessionFactory = configuration.buildSessionFactory();

		HibernateUtil.setSessionFactory(sessionFactory);

		mockServletContext = new MockServletContext("seed");
		Map<String, String> params = new HashMap<String, String>();
		params.put("ActionResolver.Packages", "net.caseydunham.seed.stripes.action");
		params.put("Extension.Packages", "net.caseydunham.seed.stripes.ext");
		params.put("Interceptor.Class", "net.caseydunham.seed.stripes.interceptor.HibernateInterceptor");
		mockServletContext.addFilter(StripesFilter.class, "StripesFilter", params);
		mockServletContext.setServlet(DispatcherServlet.class, "DispatcherServlet", null);
		mockHttpSession = new MockHttpSession(mockServletContext);

		HibernateUtil.beginTransaction();
	}

}

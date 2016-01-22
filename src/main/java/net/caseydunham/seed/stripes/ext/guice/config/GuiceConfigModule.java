package net.caseydunham.seed.stripes.ext.guice.config;

import com.google.inject.AbstractModule;
import net.caseydunham.seed.dao.ExampleDao;
import net.caseydunham.seed.dao.impl.HibernateExampleDao;
import net.caseydunham.seed.services.ExampleService;
import net.caseydunham.seed.services.impl.ExampleServiceImpl;

public class GuiceConfigModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ExampleService.class).to(ExampleServiceImpl.class);
		bind(ExampleDao.class).to(HibernateExampleDao.class);
	}

}

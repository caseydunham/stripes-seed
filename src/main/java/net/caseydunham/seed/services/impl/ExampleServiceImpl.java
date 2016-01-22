package net.caseydunham.seed.services.impl;

import com.google.inject.Inject;
import net.caseydunham.seed.dao.ExampleDao;
import net.caseydunham.seed.model.Example;
import net.caseydunham.seed.services.ExampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class ExampleServiceImpl implements ExampleService {

	private static final Logger LOG = LoggerFactory.getLogger(ExampleServiceImpl.class);

	@Inject
	private ExampleDao exampleDao;

	public ExampleDao getExampleDao() {
		return exampleDao;
	}

	public void setExampleDao(ExampleDao exampleDao) {
		this.exampleDao = exampleDao;
	}

	public Example getById(Long id) {
		return getExampleDao().getById(id);
	}

	public void create(Example p) {
		LOG.info("Creating new Example " + p);
		p.setCreated(new Date());
		getExampleDao().saveOrUpdate(p);
	}

	public List<Example> getRecentExamples() {
		return getExampleDao().getRecentExamples();
	}

}

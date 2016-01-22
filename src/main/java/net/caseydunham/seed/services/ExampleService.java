package net.caseydunham.seed.services;

import net.caseydunham.seed.dao.ExampleDao;
import net.caseydunham.seed.model.Example;

import java.util.List;

public interface ExampleService {

	Example getById(Long id);
	void create(Example p);
	List<Example> getRecentExamples();

	ExampleDao getExampleDao();
	void setExampleDao(ExampleDao exampleDao);

}

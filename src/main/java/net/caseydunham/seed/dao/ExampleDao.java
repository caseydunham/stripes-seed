package net.caseydunham.seed.dao;

import net.caseydunham.seed.model.Example;

import java.util.List;

public interface ExampleDao extends IDao<Example> {

	Example getById(Long id);
	void saveOrUpdate(Example example);
	List<Example> getRecentExamples();

}

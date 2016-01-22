package net.caseydunham.seed.dao.impl;

import net.caseydunham.seed.dao.HibernateBaseDao;
import net.caseydunham.seed.dao.ExampleDao;
import net.caseydunham.seed.model.Example;

import java.util.List;

public class HibernateExampleDao extends HibernateBaseDao<Example> implements ExampleDao {

	@SuppressWarnings("unchecked")
	public List<Example> getRecentExamples() {
		return (List<Example>) getSession().createQuery("select p from Example p order by p.created desc").list();
	}

}

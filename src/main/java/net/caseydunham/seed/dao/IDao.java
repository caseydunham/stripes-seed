package net.caseydunham.seed.dao;

import net.caseydunham.seed.model.IValueObject;

public interface IDao<T extends IValueObject> {

	T getById(Long id);
	void saveOrUpdate(T valueObject);

}

package net.caseydunham.seed.dao;

import net.caseydunham.seed.hibernate.HibernateUtil;
import net.caseydunham.seed.model.IValueObject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;

@SuppressWarnings("unchecked")
public class HibernateBaseDao<T extends IValueObject> implements IDao<T> {

	private static final Logger LOG = LoggerFactory.getLogger(HibernateBaseDao.class);

	private Class<T> persistentClass;
	private Session currentSession = null;

	public HibernateBaseDao() {
		currentSession = HibernateUtil.getCurrentSession();
		persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Session getSession() {
		return currentSession;
	}

	public void rollback() {
		Transaction t = getSession().getTransaction();
		if (t.isActive()) {
			t.rollback();
		}
	}

	public T getById(Long id) {
		return (T) getSession().get(persistentClass, id);
	}

	public void saveOrUpdate(T valueObject) {
		try {
			Session s = getSession();
			s.saveOrUpdate(valueObject);
			s.flush();
			s.refresh(valueObject);
		} catch (HibernateException e) {
			rollback();
			LOG.error("saveOrUpdate() " + valueObject.toString(), e);
			throw e;
		}
	}

}
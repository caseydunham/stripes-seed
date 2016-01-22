package net.caseydunham.seed.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {

	private static final Logger LOG = LoggerFactory.getLogger(HibernateUtil.class);

	private static SessionFactory sessionFactory;

	static void buildSessionFactory() {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			LOG.error("initial SessionFactory creation failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			buildSessionFactory();
		}
		return sessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		HibernateUtil.sessionFactory = sessionFactory;
	}

	public static void beginTransaction() {
		getCurrentSession().beginTransaction();
	}

	public static void commit() {
		Session session = getCurrentSession();
		if (session.isOpen() && !session.getTransaction().wasRolledBack()) {
			session.getTransaction().commit();
		}
	}

	public static Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	public static void rollback() {
		Session session = getCurrentSession();
		try {
			if (session.isOpen()) {
				if (session.isDirty()) {
					LOG.warn("rolling back transaction after exception");
					session.getTransaction().rollback();
				} else {
					LOG.warn("closing open transaction after exception");
					session.close();
				}
			}
		} catch (Throwable t) {
			LOG.error("failed to roll back transaction after exception, attempting to close", t);
			session.close();
		}
	}

}
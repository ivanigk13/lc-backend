package com.lawencon.base;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Agung Damas Saputra
 * 
 */
@Repository
public class AbstractJpaDao<T extends BaseEntity> {

	public Class<T> clazz;

	@SuppressWarnings("unchecked")
	public AbstractJpaDao() {
		this.clazz = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractJpaDao.class);
	}

	public T getById(final String id) {
		return em().find(clazz, id);
	}

	public List<T> getAll() {
		return em().createQuery("FROM " + clazz.getName(), clazz).getResultList();
	}

	public T save(T entity) throws Exception {
		if (entity.getId() != null) {
			entity = em().merge(entity);
		} else {
			em().persist(entity);
		}

		return entity;
	}

	protected void delete(final T entity) throws Exception {
		em().remove(entity);
	}

	public boolean deleteById(final Object entityId) throws Exception {
		T entity = null;
		if (entityId != null && entityId instanceof String) {
			entity = getById((String) entityId);
		}

		if (entity != null) {
			delete(entity);
			return true;
		}

		return false;
	}

	private EntityManager em() {
		return ConnHandler.getManager();
	}

	protected <C> TypedQuery<C> createQuery(String sql, Class<C> clazz) {
		return em().createQuery(sql, clazz);
	}

	protected Query createNativeQuery(String sql) {
		return em().createNativeQuery(sql);
	}
	
	public Long countAll() {
        return (Long) em().createQuery("SELECT COUNT(id) FROM " + clazz.getName()).getSingleResult();
    }

    public List<T> getAll(int startPage, int maxPage) {
        return em().createQuery("FROM " + clazz.getName(), clazz)
                .setFirstResult(startPage)
                .setMaxResults(maxPage)
                .getResultList();
    }

}
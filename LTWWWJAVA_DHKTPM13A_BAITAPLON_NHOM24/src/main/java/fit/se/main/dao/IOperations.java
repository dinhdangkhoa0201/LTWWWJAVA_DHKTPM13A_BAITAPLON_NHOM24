package fit.se.main.dao;

import java.io.Serializable;
import java.util.List;

public interface IOperations<T extends Serializable>{
	T findById(final int id);
	
	List<T> findAll();
	
	T create(final T enity);
	
	T update(final T entity);
	
	void delete(final T entity);
	
	void deleteById(final int entityId);
	
}

package fit.se.main.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IOperations<T extends Serializable>{
	Optional<T> findById(final long id);
	
	List<T> findAll();
	
	T create(final T enity);
	
	T update(final T entity);
	
	void delete(final T entity);
	
	void deleteById(final long entityId);
	
}

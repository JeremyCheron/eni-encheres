package fr.eni.encheres.dal;

import java.util.List;

public interface DAO<T> {
	//TODO gérer les exceptions
	T selectByID(int id);
	List<T> selectAll();
	void insert(T Object) throws Exception;
	void update(T Object) throws Exception;
	void delete(T Object) throws Exception;
}

package fr.eni.encheres.dal;

import java.util.List;
import java.util.Map;

import fr.eni.encheres.bo.Article;

public interface DAO<T> {
	T selectByID(int id) throws DALException;
	List<T> selectAll() throws DALException;
	void insert(T Object) throws DALException;
	void update(T Object) throws DALException;
	void delete(T Object) throws DALException;
	public List<T> selectByCriteria(Map<String, Object> criteria) throws DALException;
}

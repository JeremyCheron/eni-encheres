package fr.eni.encheres.dal;

import java.util.List;

public interface DAO<T> {
	//TODO gérer les exceptions
	T selectByID(int id) throws DALException;
	List<T> selectAll() throws DALException;
	void insert(T Object) throws DALException;
	void update(T Object) throws DALException;
	void delete(T Object) throws DALException;
}

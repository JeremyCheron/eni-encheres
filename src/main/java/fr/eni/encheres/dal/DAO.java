package fr.eni.encheres.dal;

import java.util.List;

public interface DAO<T> {
	//TODO gérer les exceptions
	T selectByID(int id);
	List<T> selectAll();
	void insert(T Object);
	void update(T Object);
	void delete(T Object);
}

package com.bank.dao;
import java.io.Serializable;
import java.util.List;

public interface Dao<T, I extends Serializable, S extends Serializable> {

	List<T> getAll(I id);
	T getOne(I id);
	T getOneFrmStrng(S id);
	T saveNewAccount(T obj);
	T update(T obj);
	void delete(I id);
	default boolean isUnique(T obj) {
		return true;
	}
}

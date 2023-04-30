package dao;

import java.util.Collection;

import model.Pretplata;

public interface PretplataDAO {

	void add(Pretplata pretplata) throws Exception;
	Collection<Pretplata> getAll () throws Exception;
}

package dao;

import java.util.Collection;

import model.Tarifa;

public interface TarifaDAO {

	Tarifa get (long id) throws Exception;
	Collection<Tarifa> getAll () throws Exception;
}

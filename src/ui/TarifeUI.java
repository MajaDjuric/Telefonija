package ui;

import java.util.Collection;

import dao.TarifaDAO;
import model.Tarifa;
import util.Konzola;

public class TarifeUI {

	private static TarifaDAO tarifaDAO;

	public static void setTarifaDAO(TarifaDAO tarifaDAO) {
		TarifeUI.tarifaDAO = tarifaDAO;
	}
	
	public static Tarifa pretraga () throws Exception {
		prikazSvih();
		long id = Konzola.ocitajLong("Unesite id tarife");
		Tarifa tarifa = tarifaDAO.get(id);
		if (tarifa == null) {
			System.out.println("Tarifa nije pronadjena");
		}
		return tarifa;
	}
	
	public static void prikazSvih () {
		try {
			Collection<Tarifa> tarife = tarifaDAO.getAll();
			for (Tarifa tarifa : tarife) {
				System.out.println(tarifa);
			}
		} catch (Exception e) {
			System.out.println("Doslo je do greske");
			e.printStackTrace();
		}
		
	}
}

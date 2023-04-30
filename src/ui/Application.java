package ui;

import java.sql.Connection;
import java.sql.DriverManager;

import dao.PretplataDAO;
import dao.TarifaDAO;
import init.database.DatabasePretplataDAO;
import init.database.DatabaseTarifaDAO;
import util.Meni;
import util.Meni.FunkcionalnaStavkaMenija;
import util.Meni.IzlaznaStavkaMenija;
import util.Meni.StavkaMenija;

public class Application {
private static void init () throws Exception {
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/telefonijaproba?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade", 
				"root", 
				"root");
		
		TarifaDAO tarifaDAO = new DatabaseTarifaDAO(conn);
		TarifeUI.setTarifaDAO(tarifaDAO);
		PretplataDAO pretplataDAO = new DatabasePretplataDAO(conn);
		PretplateUI.setPretplataDAO(pretplataDAO);
		PretplateUI.setTarifaDAO(tarifaDAO);
		IvestajUI.setPretplataDAO(pretplataDAO);
		IvestajUI.setTarifaDAO(tarifaDAO);

	}
	
	static {
		try {
			init();
		} catch (Exception e) {
			System.out.println("Doslo je do greske pri povezivanju sa bazom");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Meni.pokreni("Telefonija", new StavkaMenija [] {
				new IzlaznaStavkaMenija ("Izlaz"),
				new FunkcionalnaStavkaMenija ("Prikaz svih tarifa") {

					@Override
					public void izvrsi() {
						TarifeUI.prikazSvih();
					}
				},
				new FunkcionalnaStavkaMenija ("Prikaz svih pretplata") {

					@Override
					public void izvrsi() {
						PretplateUI.prikazSvih();
					}
				},
				new FunkcionalnaStavkaMenija ("Dodavanje pretplate") {

					@Override
					public void izvrsi() {
						PretplateUI.dodaj();
					}
				},
				new FunkcionalnaStavkaMenija ("Izvestaj") {

					@Override
					public void izvrsi() {
						IvestajUI.izvestaj();
					}
				},
		});
	}
}

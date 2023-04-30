package ui;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.PretplataDAO;
import dao.TarifaDAO;
import model.Pretplata;
import model.Tarifa;
import util.Konzola;

public class PretplateUI {

	private static PretplataDAO pretplataDAO;
	private static TarifaDAO tarifaDAO;
	
	public static void setPretplataDAO(PretplataDAO pretplataDAO) {
		PretplateUI.pretplataDAO = pretplataDAO;
	}
	public static void setTarifaDAO(TarifaDAO tarifaDAO) {
		PretplateUI.tarifaDAO = tarifaDAO;
	}
	
	public static void prikazSvih () {
		Collection<Tarifa> tarife;
		List<Pretplata> pretplate = new ArrayList<Pretplata>();
		try {
			tarife = tarifaDAO.getAll();
			for (Tarifa tarifa : tarife) {
				for (Pretplata pretplata  : tarifa.getPretplate()) {
					pretplate.add(pretplata);
				}
			}
			for (Pretplata pretplata : pretplate) {
				System.out.println(pretplata);
			}
		} catch (Exception e) {
			System.out.println("Doslo je do greske");
			e.printStackTrace();
		}
	
	}
	
	public static void dodaj () {
		Tarifa tarifa;
		try {
			tarifa = TarifeUI.pretraga();
			if (tarifa == null) {
				return;
			}
			String pretplatnickiBroj = Konzola.ocitajString("Unesite pretplatnicki broj");
			Collection<Tarifa> tarife = tarifaDAO.getAll();
			for (Tarifa tarifa2 : tarife) {
				for (Pretplata pretplata : tarifa.getPretplate()) {
					int trajanje = pretplata.getTrajanjeUgovora();
					LocalDateTime datumZavrsetka = pretplata.getDatumPocetka().plusMonths(trajanje);
					if (pretplata.getPretplatnickiBroj().equals(pretplatnickiBroj) &&
						datumZavrsetka.compareTo(LocalDateTime.now()) >= 0){
						System.out.println(datumZavrsetka);
						System.out.println("Vec postoji aktivan ugovor za navedeni broj");
						return;
					}
				}
			}
				
			LocalDateTime datumPocetka = Konzola.ocitajDateTime("Unesite datum pocetka");
			int trajanje = Konzola.ocitajInt("Unesite trajanje");
			if (trajanje != 12 && trajanje != 24 && trajanje != 36) {
				System.out.println("Nevazece trajanje.");
				return;
			}
			
			Pretplata pretplata = new Pretplata(tarifa, pretplatnickiBroj, datumPocetka, trajanje);
			pretplataDAO.add(pretplata);
			System.out.println("Uspesno dodavanje");

		} catch (Exception e) {
			System.out.println("Doslo je do greske");
			e.printStackTrace();
		}

		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

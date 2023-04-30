package ui;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import dao.PretplataDAO;
import dao.TarifaDAO;
import model.Pretplata;
import model.Tarifa;
import util.Konzola;

public class IvestajUI {

	private static TarifaDAO tarifaDAO;
	private static PretplataDAO pretplataDAO;
	
	public static void setTarifaDAO(TarifaDAO tarifaDAO) {
		IvestajUI.tarifaDAO = tarifaDAO;
	}
	public static void setPretplataDAO(PretplataDAO pretplataDAO) {
		IvestajUI.pretplataDAO = pretplataDAO;
	}
	
	public static void izvestaj () {
		try {
			
			LocalDateTime pocetni = Konzola.ocitajDateTime("Unesite pocetni datum");
			LocalDateTime krajnji = Konzola.ocitajDateTime("Unesite krajnji datum");
			
			
			Collection<Tarifa> tarife = tarifaDAO.getAll();
			Set<String> nazivi = new LinkedHashSet<String>();
			
			for (Tarifa tarifa : tarife) {
				nazivi.add(tarifa.getNaziv());
			}
			
			for (String naziv : nazivi) {
				double ukupanPrihod = 0;
				int najcescaDuzina = 0;
				int trajanje12 = 0;
				int trajanje24 = 0;
				int trajanje36 = 0;
				
				for (Tarifa tarifa : tarife) {
					
					for (Pretplata pretplata: tarifa.getPretplate()) {
						LocalDateTime zavrsetak = pretplata.getDatumPocetka().plusMonths(pretplata.getTrajanjeUgovora());
						if (tarifa.getNaziv().equals(naziv) &&
//							pretplata.getDatumPocetka().compareTo(pocetni) >= 0 &&
							pretplata.getDatumPocetka().compareTo(krajnji) <= 0 &&
							zavrsetak.compareTo(pocetni) >= 0) {
							
							Period periodIzmedju = Period.between(pocetni.toLocalDate().withDayOfMonth(1), krajnji.toLocalDate().withDayOfMonth(1));
							int brojMeseci = periodIzmedju.getMonths();
							
							ukupanPrihod += tarifa.getCena() * brojMeseci;
							
							if (pretplata.getTrajanjeUgovora() == 12) {
								trajanje12 ++;
							}else if (pretplata.getTrajanjeUgovora() == 24) {
								trajanje24++;
							}else if (pretplata.getTrajanjeUgovora() == 36)
								trajanje36++;
						}
						
						if (trajanje12 > trajanje24 && trajanje12 > trajanje36) {
							najcescaDuzina = 12;
						}else if(trajanje24 > trajanje12 && trajanje24 > trajanje36) {
							najcescaDuzina = 24;
						}else if (trajanje36 > trajanje24 && trajanje36 > trajanje12) {
							najcescaDuzina = 36;
						}
					}
				}
				System.out.println(naziv + "  " + ukupanPrihod + "  " + najcescaDuzina);
			}
			
			
			
		} catch (Exception e) {
			System.out.println("Doslo je do greske");
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}

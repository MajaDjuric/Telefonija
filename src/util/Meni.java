package util;

import java.util.NoSuchElementException;

/**
 * Klasa koja opisuje i izvršava meni konzolne aplikacije, oslanjajući se na
 * ispis putem {@code System.out} standardnog <i>stream</i>-a i unos putem
 * {@code System.in} standardnog <i>stream</i>-a.
 *
 * @author  Miloš Beo�?anin ({@link www.ftninformatika.com})
 * @see     com.ftninformatika.jwd.modul1.util.Meni.StavkaMenija
 * 
 */
public class Meni {

	/**
	 * Superklasa svih stavki menija.
	 * 
	 * @author  Miloš Beo�?anin ({@link www.ftninformatika.com})
	 * @see     com.ftninformatika.jwd.modul1.util.Meni
	 * 
	 */
	public static abstract class StavkaMenija {
	
		private final String tekst;

		public StavkaMenija(String tekst) throws IllegalArgumentException {
			if (tekst == null) {
				throw new IllegalArgumentException("tekst je null");
			}
			if (tekst.equals("")) {
				throw new IllegalArgumentException("tekst je prazan");
			}

			this.tekst = tekst;
		}
		
		private String formatiraj(int redniBroj) {
			return String.format("%s. %s", redniBroj, tekst);
		}

	}

	/**
	 * Klasa koja opisuje jednu izlaznu stavku menija konzolne aplikacije.
	 * 
	 * @author  Miloš Beo�?anin ({@link www.ftninformatika.com})
	 * @see     com.ftninformatika.jwd.modul1.util.Meni
	 * 
	 */
	public static class IzlaznaStavkaMenija extends StavkaMenija {
	    /**
	     * Kreira novu izlaznu stavku sa zadatim tekstom.
	     *
	     * @param      tekst   Tekst stavke koji će biti prikazan
	     *
	     * @throws  IllegalArgumentException Ako je {@code tekst} {@code null}
	     *          ili je prazan
	     * 
	     */
		public IzlaznaStavkaMenija(String tekst) throws IllegalArgumentException {
			super(tekst);
		}

	}

	/**
	 * Klasa koja opisuje i izvršava jednu stavku menija konzolne aplikacije.
	 * 
	 * @author  Miloš Beo�?anin ({@link www.ftninformatika.com})
	 * @see     com.ftninformatika.jwd.modul1.util.Meni
	 * 
	 */
	public static abstract class FunkcionalnaStavkaMenija extends StavkaMenija {
	    /**
	     * Kreira novu funkcionalnu stavku sa zadatim tekstom.
	     *
	     * @param      tekst   Tekst stavke koji će biti prikazan
	     *
	     * @throws  IllegalArgumentException Ako je {@code tekst} {@code null}
	     *          ili je prazan
	     * 
	     */
		public FunkcionalnaStavkaMenija(String tekst) throws IllegalArgumentException {
			super(tekst);
		}

	    /**
	     * Metoda koja definiše ponašanje u slu�?aju odabira ove stavke.
	     * 
	     */
		public abstract void izvrsi();
		
	}

	private final StavkaMenija[] stavke;
	private final StringBuilder meni = new StringBuilder();
	
	private void dodajPrazanRed() {
		meni.append(System.lineSeparator());
	}

	private void dodajNaslov(String naslov) {
		meni.append(naslov);
		meni.append(System.lineSeparator());
		meni.append("=".repeat(naslov.length()));
		meni.append(System.lineSeparator());
	}

	private void dodajStavku(String stavka) {
		meni.append(stavka);
		meni.append(System.lineSeparator());
	}

	private void dodajHorizontalnuLiniju(int duzina) {
		meni.append("=".repeat(duzina));
	}

    /**
     * Kreira novi meni sa zadatim naslovom i nizom stavki. Stavke će dobiti
     * redni broj na osnovu njihovog indeksa u nizu {@code stavke}.
     *
     * @param      naslov   Naslov koji će biti prikazan
     * @param      stavke   Niz objekata klase {@code StavkaMenija} koji opisuju
     *                      stavke
     *
     * @throws  IllegalArgumentException Ako su {@code naslov} ili
     *          {@code stavke} {@code null} ili ako su prazni, ili ako atribut
     *          {@code izlaz} za bar jednu stavku nije {@code true}
     *
     */
	private Meni(String naslov, StavkaMenija[] stavke) throws IllegalArgumentException {
		if (naslov == null) {
			throw new IllegalArgumentException("naslov je null");
		}
		if (naslov.equals("")) {
			throw new IllegalArgumentException("naslov je prazan");
		}
		if (stavke == null) {
			throw new IllegalArgumentException("stavke je null");
		}
		if (stavke.length <= 0) {
			throw new IllegalArgumentException("stavke su prazne");
		}
		boolean izlaz = false;
		for (StavkaMenija itStavka: stavke)
			izlaz = izlaz || itStavka instanceof IzlaznaStavkaMenija;
		if (!izlaz) {
			throw new IllegalArgumentException("izlaz za bar jednu stavku menija nije true");
		}
		
		this.stavke = stavke;

		String[] formatiraneStavke = new String[stavke.length];
		for (int it = 0; it < this.stavke.length; it++)
			formatiraneStavke[it] = this.stavke[it].formatiraj(it);

		int duzinaReda = 0;
		for (String itRed: formatiraneStavke)
			duzinaReda = Math.max(duzinaReda, itRed.length());
	
		dodajPrazanRed();
		dodajNaslov(naslov);
		
		dodajPrazanRed();
		for (String itStavka: formatiraneStavke)
			dodajStavku(itStavka);
		dodajHorizontalnuLiniju(duzinaReda);
	}

	private void pokreni() {
		boolean izlaz = false;
		while (!izlaz) {
			System.out.println(meni);

			try {
				int izbor = Konzola.ocitajInt("Unesite izbor");

				StavkaMenija stavka = stavke[izbor];
				if (izbor < 0 || izbor >= stavke.length) {
					Konzola.prikazi("Nepostojeći izbor!");
					continue;
				}
				if (stavka instanceof IzlaznaStavkaMenija) {
					izlaz = true;
				} else if (stavka instanceof FunkcionalnaStavkaMenija) {
					((FunkcionalnaStavkaMenija) stavka).izvrsi();
				}
			} catch (NoSuchElementException ex) {
				izlaz = true;
			}
		}
	}

    /**
     * Pokreće izvršavanje menija.
     * Kada korisnik odabere redni broj stavke koja nasleđuje klasu 
     * {@code FunkcionalnaStavkaMenija} izvršava se njena metoda
     * {@code izvrsi()}, a zatim se korisniku ponovo nudi odabir stavki iz
     * menija.
     * Kada korisnik odabere redni broj stavke koja nasleđuje klasu 
     * {@code IzlaznaStavkaMenija} izvršavanje menija se zaustavlja.
     * 
     * Stavke će dobiti redni broj na osnovu njihovog indeksa u nizu {@code stavke}.
     *
     * @param      naslov   Naslov koji će biti prikazan
     * @param      stavke   Niz objekata klase {@code StavkaMenija} koji opisuju
     *                      stavke
     *
     * @throws  IllegalArgumentException Ako su {@code naslov} ili
     *          {@code stavke} {@code null} ili ako su prazni, ili ako atribut
     *          {@code izlaz} za bar jednu stavku nije {@code true}
     *
     */
	public static void pokreni(String naslov, StavkaMenija[] stavke) throws IllegalArgumentException {
		Meni meni = new Meni(naslov, stavke);
		meni.pokreni();
	}

}

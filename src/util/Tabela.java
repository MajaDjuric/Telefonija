package util;

import java.util.Collection;
import java.util.List;

/**
 * Klasa koja opisuje i prikazuje tabelu u konzoli na formatiran na�?in,
 * oslanjajući se na ispis putem {@code System.out} standardnog 
 * <i>stream</i>-a.
 *
 * @author  Miloš Beo�?anin ({@link www.ftninformatika.com})
 * @see     java.lang.String
 * 
 */
public abstract class Tabela<Type> {

	private String format;

	private String zaglavlje;
	private int duzinaReda;

    /**
     * Kreira novu tabelu sa zadatim formatom za sve redove i zaglavljem.
     *
     * @param      format   Standardni formatni <i>string</i> koji će biti
     *                      korišćen za formatiranje zaglavlja i svih
     *                      redova tabele pri prikazu
     * @param      koloneZaglavlja   Niz vrednosti kojima se popunjavaju
     *                               kolone zaglavlja
     *
     * @throws  IllegalArgumentException Ako su {@code format} ili
     *          {@code koloneZaglavlja} {@code null} ili ako su prazni
     *
     */
	public Tabela(String format, Object[] koloneZaglavlja) throws IllegalArgumentException {
		if (format == null)
			throw new IllegalArgumentException("format je null");
		if (format.equals(""))
			throw new IllegalArgumentException("format je prazan");
		if (koloneZaglavlja == null)
			throw new IllegalArgumentException("koloneZaglavlja su null");
		if (koloneZaglavlja.length <= 0)
			throw new IllegalArgumentException("koloneZaglavlja su prazne");

		this.format = format;

		zaglavlje = String.format(format, koloneZaglavlja);
		duzinaReda = zaglavlje.length();
	}

    /**
     * Metoda koja uređuje relevatne podatke entiteta, koji se prikazuju
     * u svakom redu, u 2D niz. Poziva se pre prikaza svakog reda tabele.
     * 
     * @param      vrednost   Jedan entitet koji se prikazuje u tekućem
     *                        redu u tabeli.
     *                        
     * @return     Uređeni 2D niz vrednosti koji sadrži relevatne podatke
     *             entiteta {@code vrednost} koji se prikazuje u tekućem
     *             redu tabele.
     * 
     */
	protected abstract List<Object[]> uredi(Type vrednost);

	private static void dodajPrazanRed(StringBuilder tabela) {
		tabela.append(System.lineSeparator());
	}
	
	private void dodajZaglavlje(StringBuilder tabela) {
		tabela.append(zaglavlje);
		tabela.append(System.lineSeparator());
		tabela.append("=".repeat(duzinaReda));
		tabela.append(System.lineSeparator());
	}

	private void dodajRed(StringBuilder tabela, Type sadrzaj) {
		List<Object[]> uredjeniSadrzaj = uredi(sadrzaj);

		for (int red = 0; red < uredjeniSadrzaj.size(); red++) {
			Object[] redSadrzaja = new Object[uredjeniSadrzaj.get(0).length];
			for (int kolona = 0; kolona < redSadrzaja.length; kolona++)
				redSadrzaja[kolona] = (uredjeniSadrzaj.get(red)[kolona] != null)? uredjeniSadrzaj.get(red)[kolona]: "";

			tabela.append(String.format(format, redSadrzaja));
			tabela.append(System.lineSeparator());
		}	
		tabela.append("-".repeat(duzinaReda));
		tabela.append(System.lineSeparator());
	}

    /**
     * Tabelarno, na formatiran na�?in, prikazuje jedan objekat 
     * ispod zaglavlja tabele.
     * 
     * @param      sadrzaj   Entitet koji se prikazuje.
     *
     * @throws  IllegalArgumentException Ako je {@code sadrzaj}
     *          {@code null}
     * 
     */
	public void prikazi(Type sadrzaj) throws IllegalArgumentException {
		if (sadrzaj == null)
			throw new IllegalArgumentException("sadrzaj je null");

		StringBuilder tabela = new StringBuilder();
		dodajPrazanRed(tabela);
		dodajZaglavlje(tabela);
		dodajRed(tabela, sadrzaj);
		
		System.out.print(tabela);
	}

    /**
     * Tabelarno, na formatiran na�?in, prikazuje kolekciju objekata,
     * svaki u posebnom redu ispod zaglavlja tabele.
     * 
     * @param      sadrzaj   Kolekcija objekata koji se prikazuju.
     *
     * @throws  IllegalArgumentException Ako je {@code sadrzaj}
     *          {@code null}
     * 
     */
	public void prikazi(Collection<Type> sadrzaj) {
		if (sadrzaj == null)
			throw new IllegalArgumentException("sadrzaj je null");

		StringBuilder tabela = new StringBuilder();
		dodajPrazanRed(tabela);
		dodajZaglavlje(tabela);
		for (Type itSadrzaj: sadrzaj)
			dodajRed(tabela, itSadrzaj);
		
		System.out.print(tabela);
	}

}

package model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Tarifa {

	private final long id;
	private String naziv;
	private String opis;
	private double cena;
	final Set<Pretplata> pretplate = new HashSet<Pretplata>();
	
	public Tarifa(long id, String naziv, String opis, double cena) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.cena = cena;
	}
	public Tarifa(String naziv, String opis, double cena) {
		super();
		this.id = 0;
		this.naziv = naziv;
		this.opis = opis;
		this.cena = cena;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarifa other = (Tarifa) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Tarifa [id=" + id + ", naziv=" + naziv + ", opis=" + opis + ", cena=" + cena + "]";
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public long getId() {
		return id;
	}
	public Collection<Pretplata> getPretplate() {
		return Collections.unmodifiableCollection(pretplate);
	}
	public void dodajJednuPeetplatu (Pretplata pretplata) {
		this.pretplate.add(pretplata);
	}
	
}

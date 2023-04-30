package model;

import java.time.LocalDateTime;
import java.util.Objects;

import util.Konzola;

public class Pretplata {

	private final long id;
	Tarifa tarifa;
	private String pretplatnickiBroj;
	private LocalDateTime datumPocetka;
	private int trajanjeUgovora;
	
	public Pretplata(long id, Tarifa tarifa, String pretplatnickiBroj, LocalDateTime datumPocetka,
			int trajanjeUgovora) {
		super();
		this.id = id;
		this.tarifa = tarifa;
		this.pretplatnickiBroj = pretplatnickiBroj;
		this.datumPocetka = datumPocetka;
		this.trajanjeUgovora = trajanjeUgovora;
	}
	public Pretplata(Tarifa tarifa, String pretplatnickiBroj, LocalDateTime datumPocetka,
			int trajanjeUgovora) {
		super();
		this.id = 0;
		this.tarifa = tarifa;
		this.pretplatnickiBroj = pretplatnickiBroj;
		this.datumPocetka = datumPocetka;
		this.trajanjeUgovora = trajanjeUgovora;
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
		Pretplata other = (Pretplata) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Pretplata [id=" + id + ", tarifa=" + tarifa.getNaziv() + ", pretplatnickiBroj=" + pretplatnickiBroj
				+ ", datumPocetka=" + Konzola.formatiraj(datumPocetka) + ", trajanjeUgovora=" + trajanjeUgovora + "ukupanPrihod= " + 
				(tarifa.getCena() * trajanjeUgovora) + "]";
	}
	public Tarifa getTarifa() {
		return tarifa;
	}
	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}
	public String getPretplatnickiBroj() {
		return pretplatnickiBroj;
	}
	public void setPretplatnickiBroj(String pretplatnickiBroj) {
		this.pretplatnickiBroj = pretplatnickiBroj;
	}
	public LocalDateTime getDatumPocetka() {
		return datumPocetka;
	}
	public void setDatumPocetka(LocalDateTime datumPocetka) {
		this.datumPocetka = datumPocetka;
	}
	public int getTrajanjeUgovora() {
		return trajanjeUgovora;
	}
	public void setTrajanjeUgovora(int trajanjeUgovora) {
		this.trajanjeUgovora = trajanjeUgovora;
	}
	public long getId() {
		return id;
	}
	
}

package init.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.mysql.cj.protocol.Resultset;

import dao.TarifaDAO;
import model.Pretplata;
import model.Tarifa;

public class DatabaseTarifaDAO implements TarifaDAO {

	private final Connection conn;

	public DatabaseTarifaDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public Collection<Tarifa> getAll() throws Exception {
		Map<Long, Tarifa> tarife = new HashMap<Long, Tarifa>();
		String sql = "SELECT t.id, t.naziv, t.opis, t.cena, \r\n"
				+ "p.id, p.pretplatnickiBroj, p.datumPocetka, p.trajanjeUgovora \r\n"
				+ "FROM tarife t LEFT JOIN pretplate p ON t.id = p.tarifaId";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					long tid = rset.getLong(++kolona);
					String naziv = rset.getString(++kolona);
					String opis = rset.getString(++kolona);
					double cena = rset.getDouble(++kolona);
					
					Tarifa tarifa = tarife.get(tid);
					if (tarifa == null) {
						tarifa = new Tarifa(tid, naziv, opis, cena);
						tarife.put(tarifa.getId(), tarifa);
					}
					
					long pid = rset.getLong(++kolona);
					if (pid != 0) {
						String pretplatnickiBroj = rset.getString(++kolona);
						LocalDateTime datum = rset.getTimestamp(++kolona).toLocalDateTime();
						int trajanjeUgovora = rset.getInt(++kolona);
						Pretplata pretplata = new Pretplata(pid, tarifa, pretplatnickiBroj, datum, trajanjeUgovora);
						tarifa.dodajJednuPeetplatu(pretplata);
					}
				}
			}
		}
		
		return tarife.values();
	}

	@Override
	public Tarifa get(long id) throws Exception {
		Tarifa tarifa = null;
		String sql = "SELECT t.naziv, t.opis, t.cena, \r\n"
				+ "p.id, p.pretplatnickiBroj, p.datumPocetka, p.trajanjeUgovora \r\n"
				+ "FROM tarife t LEFT JOIN pretplate p ON t.id = p.tarifaId"
				+ " WHERE t.id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setLong(1, id);
			try(ResultSet rset = stmt.executeQuery()){
				while(rset.next()) {
					int kolona = 0;
					String naziv = rset.getString(++kolona);
					String opis = rset.getString(++kolona);
					double cena = rset.getDouble(++kolona);
					
					if (tarifa == null) {
						tarifa = new Tarifa(id, naziv, opis, cena);
					}
					
					long pid = rset.getLong(++kolona);
					if (pid != 0) {
						String pretplatnickiBroj = rset.getString(++kolona);
						LocalDateTime datum = rset.getTimestamp(++kolona).toLocalDateTime();
						int trajanjeUgovora = rset.getInt(++kolona);
						Pretplata pretplata = new Pretplata(pid, tarifa, pretplatnickiBroj, datum, trajanjeUgovora);
						tarifa.dodajJednuPeetplatu(pretplata);
					}
				}
			}
		}
		
		return tarifa;
	}
	
}

package init.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import dao.PretplataDAO;
import model.Pretplata;
import model.Tarifa;

public class DatabasePretplataDAO implements PretplataDAO{

	private final Connection conn;

	public DatabasePretplataDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void add(Pretplata pretplata) throws Exception {
		String sql = "INSERT INTO pretplate (tarifaId, pretplatnickiBroj, datumPocetka, trajanjeUgovora) VALUES (?, ?, ?, ?)";
		try(PreparedStatement stmt = conn.prepareStatement(sql)){
			int param = 0;
			stmt.setLong(++param, pretplata.getTarifa().getId());
			stmt.setString(++param, pretplata.getPretplatnickiBroj());
			stmt.setTimestamp(++param, Timestamp.valueOf(pretplata.getDatumPocetka()));
			stmt.setInt(++param, pretplata.getTrajanjeUgovora());
			
			stmt.executeUpdate();
		}
		
	}


	@Override
	public Collection<Pretplata> getAll() throws Exception {
		Map<Long, Pretplata> pretplate= new HashMap<Long, Pretplata>();
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
						
						Tarifa tarifa = new Tarifa(tid, naziv, opis, cena);
				
					
					long pid = rset.getLong(++kolona);
					if (pid != 0) {
						String pretplatnickiBroj = rset.getString(++kolona);
					LocalDateTime datum = rset.getTimestamp(++kolona).toLocalDateTime();
					int trajanjeUgovora = rset.getInt(++kolona);
					Pretplata pretplata = pretplate.get(pid);
					if (pretplata == null) {
						pretplata = new Pretplata(pid, tarifa, pretplatnickiBroj, datum, trajanjeUgovora);
						pretplate.put(pretplata.getId(), pretplata);
					}
					}
					
					}
				}
			}
		
		return pretplate.values();
	}
	
}

package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Iscritti;
import it.polito.tdp.lab04.model.Studente;

public class IscrizioneDAO {

	public List<Studente> getIscrittiCorso(String codins) {

		final String sql = "select s.matricola, s.cognome, s.nome, s.CDS "
				+"from iscrizione i, studente s "
				+"where i.codins = ? and i.matricola = s.matricola";

		List<Studente> iscritti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, codins); //IMPORTANTE PERCHE' SENNO' IL PROGRAMMA NON CAPISCE DA QUANDO INIZIARE A LEGGERE
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				//aggiungo studenti iscritti al corso
				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");
				
				iscritti.add(new Studente(matricola, cognome, nome, cds));
			}

			conn.close();
			
			return iscritti;
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public boolean verificaIscrizione(String codins, int matricola) {
		//List<Iscritti> iscrizioni = new LinkedList<Iscritti>();
		Iscritti iscrizione = null;
		String sql = "select * from iscrizione where matricola = ? and codins = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			st.setString(2, codins);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				int m = rs.getInt("matricola");
				String c = rs.getString("codins");
				//iscrizioni.add(new Iscritti(m,c));
				iscrizione = new Iscritti(m,c);
			}
			conn.close();	
		}catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		if(iscrizione !=null) {
			return true;
		}else
			return false;
	}

}

package it.polito.tdp.lab04.DAO;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;
public class StudenteDAO {

	public List<Studente> getTuttiStudenti(){
		List<Studente> s = new LinkedList<Studente>();
		
		String sql = "select * from studente";
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()==true) {
				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");
				
				s.add(new Studente(matricola, cognome, nome, CDS));
			}
			
		}catch(SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		return s;
	}
	
	public List<Corso> getCorsiStudente(int matricola){
		List<Corso> corsi = new LinkedList<Corso>();
		
		String sql = "select c.codins, c.crediti, c.nome, c.pd "
				+"from corso c, iscrizione i "
				+"where c.codins = i.codins and i.matricola = ? "
				+"group by c.codins, c.crediti, c.nome, c.pd";
		try {
			Connection con = ConnectDB.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, matricola);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()==true) {
				String codins = rs.getString("codins");
				int crediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int pd = rs.getInt("pd");
				
				corsi.add(new Corso(codins, crediti, nome, pd));
			}
			
		}catch(SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		return corsi;
	}
}

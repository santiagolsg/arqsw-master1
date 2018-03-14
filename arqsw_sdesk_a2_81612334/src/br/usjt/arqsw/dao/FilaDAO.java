package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Fila;
/**
 * 
 * @author Leonardo Santiago Gonçalves 81612334 SI3AN-MCA1
 *
 */
@Repository
public class FilaDAO {
	private Connection conn;
	
	@Autowired
    public FilaDAO(DataSource dataSource) throws IOException{
        try{
            this.conn = dataSource.getConnection();
        } catch (SQLException e){
            throw new IOException(e);
        }
    }
	

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Fila> listarFilas() throws IOException {
		String query = "select id_fila, nm_fila from fila";
		ArrayList<Fila> lista = new ArrayList<>();
		try(PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();){
			
			while(rs.next()) {
				Fila fila = new Fila();
				fila.setId(rs.getInt("id_fila"));
				fila.setNome(rs.getString("nm_fila"));
				lista.add(fila);
			}
			
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return lista;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws IOException
	 */
	public Fila carregar(int id) throws IOException {
		Fila fila = new Fila();
		fila.setId(id);
		String query = "select nm_fila from fila where id_fila=?";

		try (PreparedStatement pst = conn.prepareStatement(query);) {
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery();) {

				if (rs.next()) {
					fila.setNome(rs.getString("nm_fila"));
				} else {
					fila.setNome(null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return fila;
	}

}

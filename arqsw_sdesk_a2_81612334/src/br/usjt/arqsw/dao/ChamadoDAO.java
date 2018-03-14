package br.usjt.arqsw.dao;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
/**
 * 
 * @author Leonardo Santiago Gonçalves 81612334 SI3AN-MCA1
 *
 */


@Repository
public class ChamadoDAO {
	private Connection conn;
	
	@Autowired
    public ChamadoDAO(DataSource dataSource) throws IOException{
        try{
            this.conn = dataSource.getConnection();
        } catch (SQLException e){
            throw new IOException(e);
        }
    }
	
	public ArrayList<Chamado> listarChamados(Fila fila) throws IOException{
		ArrayList<Chamado> lista = new ArrayList<>();
		String query = "select c.id_chamado, c.descricao, c.dt_abertura, f.nm_fila "+
				"from chamado c, fila f where c.id_fila = f.id_fila and c.id_fila=?";
		
		try(PreparedStatement pst = conn.prepareStatement(query);){
			pst.setInt(1, fila.getId());
			
			try(ResultSet rs = pst.executeQuery();){
				while(rs.next()){
					Chamado chamado = new Chamado();
					chamado.setNumero(rs.getInt("id_chamado"));
					chamado.setDescricao(rs.getString("descricao"));
					chamado.setDataAbertura(rs.getDate("dt_abertura"));
					fila.setNome(rs.getString("nm_fila"));
					chamado.setFila(fila);
					lista.add(chamado);
				}
			} catch(SQLException e){
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch(SQLException e){
			e.printStackTrace();
			throw new IOException(e);
		}
		return lista;
	}
	 
	
	 public int criarChamado(Chamado chamado) throws IOException {
	        Date dataAbertura = new Date(chamado.getDataAbertura().getTime());

	        String sqlInsert = "INSERT INTO chamado(descricao, status, dt_abertura, id_fila) VALUES (?, ?, ?, ?)";
	        try(PreparedStatement pst = conn.prepareStatement(sqlInsert);){
	            pst.setString(1, chamado.getDescricao());
	            pst.setString(2, chamado.getStatus());
	            pst.setDate(3, (java.sql.Date) dataAbertura);
	            pst.setInt(4, chamado.getFila().getId());

	            pst.execute();

	            String sqlQuery = "SELECT LAST_INSERT_ID()";
	            try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); ResultSet rs = stm2.executeQuery();) {
	                if (rs.next()) {
	                    chamado.setId(rs.getInt(1));
	                }
	            }

	        } catch (SQLException e) {
	            throw new IOException(e);
	        }
	        return chamado.getId();
	    }
	
}

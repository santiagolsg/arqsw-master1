package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.usjt.arqsw.dao.FilaDAO;
import br.usjt.arqsw.entity.Fila;

/**
 * 
 * @author Leonardo Santiago Gonçalves 81612334 SI3AN-MCA1
 *
 */

@Service
public class FilaService {
	private FilaDAO dao;
	
	public FilaService(FilaDAO dao) {
		this.dao = dao;
	}
	public ArrayList<Fila> listarFilas() throws IOException{
		return dao.listarFilas();
	}
	public Fila carregar(int id) throws IOException{
		return dao.carregar(id);
	}
}

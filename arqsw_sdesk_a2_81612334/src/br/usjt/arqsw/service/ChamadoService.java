package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.usjt.arqsw.dao.ChamadoDAO;
import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;

/**
 * 
 * @author Leonardo Santiago Gonçalves 816123344 SI3AN-MCA1
 * 
 *
 */

@Service
public class ChamadoService {
	ChamadoDAO dao;
	
	public ChamadoService(ChamadoDAO dao){
		this.dao = dao;
	}
	
	public int novoChamado(Chamado chamado) throws IOException{
		chamado.setDataAbertura(new Date());
		chamado.setDataFechamento(null);
		chamado.setStatus(Chamado.ABERTO);
		return -1;
		//return dao.inserirChamado(chamado);
	}
	
	public ArrayList<Chamado> listarChamados(Fila fila) throws IOException{
		return dao.listarChamados(fila);
	}

}

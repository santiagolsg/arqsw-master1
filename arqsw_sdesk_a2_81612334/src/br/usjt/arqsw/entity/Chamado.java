package br.usjt.arqsw.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Leonardo Santiago Gon�alves 81612334 SI3AN-MCA1
 *
 */

@Entity
public class Chamado {
	private int numero;
	
	private Date dataAbertura;
	
	private Date dataFechamento;
	
	private String status;
	
	@NotNull 
	@Size(max=100,min=10, message="O tamanho da descrição deve estar entre 10 e 100 caracteres")
	private String descricao;
	
	@NotNull
	private Fila fila;
	
	
	public static final String ABERTO = "aberto";
	public static final String FECHADO = "fechado";
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Date getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public Date getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Fila getFila() {
		return fila;
	}
	public void setFila(Fila fila) {
		this.fila = fila;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int getTempoDias(){
		//getTime e currentTimeMillis retornam o tempo em milisegundos
		//dividir por 1000 * 60 * 60 * 24 converte para dias
		int dias;
		if(dataFechamento == null){
			//considera o momento atual para calcular o tempo aberto
			dias =  (int)(System.currentTimeMillis() - dataAbertura.getTime())/(1000 * 60 * 60 * 24);
		} else {
			//considera a data de fechamento para calcular o tempo aberto
			dias = (int)(dataFechamento.getTime() - dataAbertura.getTime())/(1000 * 60 * 60 * 24);
		}
		return dias;
	}
	
	@Override
	public String toString() {
		return "Chamado [numero=" + numero + ", dataAbertura=" + dataAbertura
				+ ", dataFechamento=" + dataFechamento + ", status=" + status
				+ ", descricao=" + descricao + ", fila=" + fila + "]";
	}


}

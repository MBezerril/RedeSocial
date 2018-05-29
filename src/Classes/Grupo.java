package Classes;

import java.util.Date;

public class Grupo {
	private int ID;
	private int IDUsuarioAdm;
	private String Tipo;
	private String Nome;
	private Date DataCriacao;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getIDUsuarioAdm() {
		return IDUsuarioAdm;
	}
	public void setIDUsuarioAdm(int iDUsuarioAdm) {
		IDUsuarioAdm = iDUsuarioAdm;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public Date getDataCriacao() {
		return DataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		DataCriacao = dataCriacao;
	}
	
}

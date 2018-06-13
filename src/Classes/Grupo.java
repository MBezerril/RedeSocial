package Classes;

import java.util.Date;

public class Grupo {
	private int ID;
	private int IDUsuarioAdm;
	private String Nome;
	private String Foto;
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

	public String getFoto() {
		return Foto;
	}

	public void setFoto(String foto) {
		Foto = foto;
	}

}

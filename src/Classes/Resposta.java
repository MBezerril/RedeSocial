package Classes;

import java.util.Date;

public class Resposta {
	private int ID;
	private int IDPublicacao;
	private int IDAutor;
	private Date Data;
	private String Imagem;
	private String Texto;

	public String getImagem() {
		return Imagem;
	}

	public void setImagem(String imagem) {
		Imagem = imagem;
	}

	public Date getData() {
		return Data;
	}

	public void setData(Date data) {
		Data = data;
	}

	public String getTexto() {
		return Texto;
	}

	public void setTexto(String texto) {
		Texto = texto;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Date getDataCriacao() {
		return Data;
	}

	public void setDataCriacao(Date dataCriacao) {
		Data = dataCriacao;
	}

	public int getIDPublicacao() {
		return IDPublicacao;
	}

	public void setIDPublicacao(int iDPublicacao) {
		IDPublicacao = iDPublicacao;
	}

	public int getIDAutor() {
		return IDAutor;
	}

	public void setIDAutor(int iDAutor) {
		IDAutor = iDAutor;
	}

}

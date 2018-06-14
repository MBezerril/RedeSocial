package Classes;

import java.util.Date;

public class Publicacao {
	private int ID;
	private int IDUsuarioAutor;
	private String NomeIDAutor;
	private int IDDestino;
	private int Visibilidade;
	private String Imagem;
	private String Texto;
	private Date Data;

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

	public int getIDUsuarioAutor() {
		return IDUsuarioAutor;
	}

	public void setIDUsuarioAutor(int iDUsuarioAutor) {
		IDUsuarioAutor = iDUsuarioAutor;
	}

	public int getIDDestino() {
		return IDDestino;
	}

	public void setIDDestino(int iDDestino) {
		IDDestino = iDDestino;
	}

	public int getVisibilidade() {
		return Visibilidade;
	}

	public void setVisibilidade(int visibilidade) {
		Visibilidade = visibilidade;
	}

	public String getNomeIDAutor() {
		return NomeIDAutor;
	}

	public void setNomeIDAutor(String nomeIDAutor) {
		NomeIDAutor = nomeIDAutor;
	}


}

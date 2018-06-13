package Classes;

import java.util.Date;

public class Usuario {
	private int IDUsuario;
	private String NomeCompleto;
	private String Login;
	private String Password;
	private Date DataNascimento;
	private String Foto;
	private String Endereco;
	private String Trabalho;

	public Usuario(String nome, String trabalho, String endereco, String login, String senha) {
		this.Login = login;
		this.Password = senha;
		this.NomeCompleto = nome;
		this.Trabalho = trabalho;
		this.Endereco = endereco;
	}

	public Usuario() {
	}

	public int getIDUsuario() {
		return IDUsuario;
	}

	public void setIDUsuario(int iDUsuario) {
		IDUsuario = iDUsuario;
	}

	public String getNomeCompleto() {
		return NomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		NomeCompleto = nomeCompleto;
	}

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		Endereco = endereco;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Date getDataNascimento() {
		return DataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		DataNascimento = dataNascimento;
	}

	public String getFoto() {
		return Foto;
	}

	public void setFoto(String foto) {
		Foto = foto;
	}

	public String getTrabalho() {
		return Trabalho;
	}

	public void setTrabalho(String trabalho) {
		Trabalho = trabalho;
	}

}

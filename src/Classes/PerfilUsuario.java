package Classes;

public class PerfilUsuario {
	private int IDUsuario;
	private String NomeCompleto;
	private String Email;
	private String Telefone1;
	private String Telefone2;
	private String Endereco;
	
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
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getTelefone1() {
		return Telefone1;
	}
	public void setTelefone1(String telefone1) {
		Telefone1 = telefone1;
	}
	public String getTelefone2() {
		return Telefone2;
	}
	public void setTelefone2(String telefone2) {
		Telefone2 = telefone2;
	}
	public String getEndereco() {
		return Endereco;
	}
	public void setEndereco(String endereco) {
		Endereco = endereco;
	}
}

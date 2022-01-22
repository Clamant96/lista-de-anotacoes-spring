package br.com.helpconnect.minhaLista.model;

public class UsuarioLogin {
	
	private long id;
	
	private String username;
	
	private String senha;
	
	private Lista lista;
	
	private String token;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Lista getLista() {
		return lista;
	}
	
	public void setLista(Lista lista) {
		this.lista = lista;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}

package io.github.zbrant.testesunitarios.entidades;

import java.util.Objects;

public class Usuario {
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Usuario usuario = (Usuario) o;
		return Objects.equals(nome, usuario.nome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	private String nome;
	
	public Usuario() {}
	
	public Usuario(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return "Usuario{" +
				"nome='" + nome + '\'' +
				'}';
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
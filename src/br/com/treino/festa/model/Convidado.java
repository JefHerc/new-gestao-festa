package br.com.treino.festa.model;

public class Convidado {

	private int id;
	private String nome;

	private int quantidadeAcompanhantes;

	public Convidado(int id, String nome, int quantidadeAcompanhantes) {
		this.id = id;
		this.nome = nome;
		this.quantidadeAcompanhantes = quantidadeAcompanhantes;
	}
	
	public Convidado(String nome, int quantidadeAcompanhantes) {
		this.nome = nome;
		this.quantidadeAcompanhantes = quantidadeAcompanhantes;
	}

	public Convidado() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidadeAcompanhantes() {
		return quantidadeAcompanhantes;
	}

	public void setQuantidadeAcompanhantes(int quantidadeAcompanhantes) {
		this.quantidadeAcompanhantes = quantidadeAcompanhantes;
	}

	@Override
		public String toString() {
			return String.format("Id: %d, Nome: %s, Qtd: %d", this.id, this.nome, this.quantidadeAcompanhantes);
		}
}

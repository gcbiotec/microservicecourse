package br.com.fundatec.microservicecourse.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cachorro")
public class Cachorro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private int idade;
    
    @Column(name = "Raça")
    private String raca;
    
    @Column(name = "peso")
    private int peso;
    
        
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario meuVeterinario;
    
    public Veterinario getMeuVeterinario() {
		return meuVeterinario;
	}

	public void setMeuVeterinario(Veterinario meuVeterinario) {
		this.meuVeterinario = meuVeterinario;
	}

	public Cachorro(Veterinario meuVeterinario) {
		super();
		this.meuVeterinario = meuVeterinario;
	}

	public Cachorro() {}
    
    public Cachorro(Long id, String nome, int idade, String raca, int peso) {
		super();
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.raca = raca;
		this.peso = peso;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	
  }

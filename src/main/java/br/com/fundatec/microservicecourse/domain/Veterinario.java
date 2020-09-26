package br.com.fundatec.microservicecourse.domain;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "veterinario")

public class Veterinario implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "dataNascimento")
    private String dataNascimento;
    
    @Column(name = "cpf", unique = true)
    private String cpf;
    
        
    @OneToMany(
            mappedBy = "veterinario",
            targetEntity = Cachorro.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    private List<Cachorro> cachorros;
    
    public Veterinario() {};

	public Veterinario(Long id, String nome, String dataNascimento, String cpf, List<Cachorro> cachorros) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.cachorros = cachorros;
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

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Cachorro> getCachorros() {
		return cachorros;
	}

	public void setCachorros(List<Cachorro> cachorros) {
		this.cachorros = cachorros;
	}
        
}


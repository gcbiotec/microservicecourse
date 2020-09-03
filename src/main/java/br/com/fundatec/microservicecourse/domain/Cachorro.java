package br.com.fundatec.microservicecourse.domain;

import javax.persistence.*;

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

    public Cachorro() {}

    public Cachorro(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    //fazendo alteraçãoaskd asd
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

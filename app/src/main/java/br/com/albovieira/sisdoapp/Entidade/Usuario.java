package br.com.albovieira.sisdoapp.Entidade;

/**
 * Created by albov on 29/08/2015.
 */
public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Long perfil;

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String senha, Long perfil) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getPerfil() {
        return perfil;
    }

    public void setPerfil(Long perfil) {
        this.perfil = perfil;
    }
}

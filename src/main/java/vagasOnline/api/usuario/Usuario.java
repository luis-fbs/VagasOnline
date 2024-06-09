package vagasOnline.api.usuario;

import vagasOnline.api.vaga.Vaga;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    private String foto, nome, senha;
    private List<Vaga> vagasCadastradas;

    public Usuario(String foto, String nome, String senha) {
        this.foto = foto;
        this.nome = nome;
        this.senha = senha;
        this.vagasCadastradas = new ArrayList<Vaga>();
    }

    public String getFoto() {
        return foto;
    }

    public String getNome() {
        return nome;
    }

    public List<Vaga> getVagasCadastradas() {
        return vagasCadastradas;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void adiconarVagaNaListaDeCadastradas(Vaga vaga){
        this.vagasCadastradas.add(vaga);
    }

    public void excluiVagaDaListaDeCadastradas(Vaga vaga){
        this.vagasCadastradas.remove(vaga);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Usuario) && this.senha.equals( ((Usuario) obj).senha );
    }
}

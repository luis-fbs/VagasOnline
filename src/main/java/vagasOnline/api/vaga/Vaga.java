package vagasOnline.api.vaga;

import vagasOnline.api.usuario.Empresa;
import vagasOnline.api.usuario.Pessoa;

import java.util.List;

public class Vaga {
    private Empresa empresa;
    private String cargo, descricao;
    private List<Pessoa> candidatos;

    public Vaga(String cargo, String descricao, Empresa empresa) {
        this.cargo = cargo;
        this.descricao = descricao;
        this.empresa = empresa;
    }

    public void adicionarCandidato(Pessoa candidato){
        this.candidatos.add(candidato);
    }

    public void excluirCandidato(Pessoa candidato){
        this.candidatos.remove(candidato);
    }
}
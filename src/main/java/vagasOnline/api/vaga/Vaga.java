package vagasOnline.api.vaga;

import vagasOnline.api.usuario.Empresa;
import vagasOnline.api.usuario.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class Vaga {
    private Empresa empresa;
    private String cargo, descricao;
    private List<Pessoa> candidatos;

    public Vaga(String cargo, String descricao, Empresa empresa) {
        this.cargo = cargo;
        this.descricao = descricao;
        this.empresa = empresa;
        this.candidatos = new ArrayList<Pessoa>();
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void inicializaListaCandidatos(){
        if (candidatos == null){
            candidatos = new ArrayList<Pessoa>();
        }
    }
    
    public void adicionarCandidato(Pessoa candidato){
        this.candidatos.add(candidato);
    }

    public void excluirCandidato(Pessoa candidato){
        this.candidatos.remove(candidato);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Vaga) &&
                ((Vaga) obj).cargo.equals(this.cargo) &&
                ((Vaga) obj).empresa.equals(this.empresa) &&
                ((Vaga) obj).descricao.equals(this.descricao);
    }
}
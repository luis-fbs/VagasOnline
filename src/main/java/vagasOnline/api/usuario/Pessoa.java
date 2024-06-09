package vagasOnline.api.usuario;

import vagasOnline.api.vaga.Vaga;

public class Pessoa extends Usuario{
    private String cpf, curriculo;

    public Pessoa(String foto, String nome, String senha, String cpf, String curriculo) {
        super(foto, nome, senha);
        this.cpf = cpf;
        this.curriculo = curriculo;
    }

    public String getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(String curriculo) {
        this.curriculo = curriculo;
    }

    public void cadastarEmVaga(Vaga vaga){
        this.adiconarVagaNaListaDeCadastradas(vaga);
    }

    public void desistirDaVaga(Vaga vaga){
        this.excluiVagaDaListaDeCadastradas(vaga);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) &&
                (obj instanceof Pessoa) &&
                this.cpf.equals( ((Pessoa) obj).cpf );
    }
}
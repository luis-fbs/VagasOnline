package vagasOnline.api.usuario;

import vagasOnline.api.vaga.Vaga;

public class Empresa extends Usuario{
    private String cnpj;

    public Empresa(String foto, String nome, String senha, String cnpj) {
        super(foto, nome, senha);
        this.cnpj = cnpj;
    }

    public void cadastrarVaga(Vaga vaga){
        this.adiconarVagaNaListaDeCadastradas(vaga);
    }

    public void excluirVaga(Vaga vaga){
        this.excluiVagaDaListaDeCadastradas(vaga);
    }
}

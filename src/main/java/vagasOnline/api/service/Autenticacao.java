package vagasOnline.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vagasOnline.api.dto.AutenticacaoDTO;
import vagasOnline.api.usuario.Empresa;
import vagasOnline.api.usuario.Pessoa;
import vagasOnline.api.util.Conversor;

@Service
public class Autenticacao {
    GerenciadorDeCadastro gerenciadorDeCadastro;

    @Autowired
    public Autenticacao(GerenciadorDeCadastro gerenciadorDeCadastro) {
        this.gerenciadorDeCadastro = gerenciadorDeCadastro;
    }

    public String autenticarPessoa(String json){
        for (Pessoa pessoa: gerenciadorDeCadastro.getPessoasCadastradas()){
            AutenticacaoDTO autenticacaoDTO = Conversor.jsonParaAutenticacaoDTO(json);
            if (autenticacaoDTO != null) {
                if (pessoa.autenticacaoCorreta(autenticacaoDTO.identificacao(), autenticacaoDTO.senha())) {
                    return Conversor.usuarioParaJson(pessoa);
                }
            }
        }
        return null;
    }

    public String autenticarEmpresa(String json) {
        for (Empresa empresa: gerenciadorDeCadastro.getEmpresasCadastradas()){
            AutenticacaoDTO autenticacaoDTO = Conversor.jsonParaAutenticacaoDTO(json);
            if (autenticacaoDTO != null) {
                if (empresa.autenticacaoCorreta(autenticacaoDTO.identificacao(), autenticacaoDTO.senha())) {
                    return Conversor.usuarioParaJson(empresa);
                }
            }
        }
        return null;
    }
}

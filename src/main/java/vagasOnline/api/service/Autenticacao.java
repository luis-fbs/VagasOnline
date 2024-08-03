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
        AutenticacaoDTO autenticacaoDTO = Conversor.jsonParaAutenticacaoDTO(json);
        if (autenticacaoDTO != null) {
            for (Pessoa pessoa : gerenciadorDeCadastro.getPessoasCadastradas()) {
                if (pessoa.autenticacaoCorreta(autenticacaoDTO.identificacao(), autenticacaoDTO.senha())) {
                    return Conversor.usuarioParaJson(pessoa);
                }
            }
        }
        return null;
    }

    public String autenticarEmpresa(String json) {
        AutenticacaoDTO autenticacaoDTO = Conversor.jsonParaAutenticacaoDTO(json);
        if (autenticacaoDTO != null) {
            for (Empresa empresa : gerenciadorDeCadastro.getEmpresasCadastradas()) {
                if (empresa.autenticacaoCorreta(autenticacaoDTO.identificacao(), autenticacaoDTO.senha())) {
                    return Conversor.usuarioParaJson(empresa);
                }
            }
        }
        return null;
    }
}

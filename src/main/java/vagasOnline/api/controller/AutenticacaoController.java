package vagasOnline.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vagasOnline.api.service.Autenticacao;

@RestController
public class AutenticacaoController {
    Autenticacao autenticacao;

    @Autowired
    public AutenticacaoController(Autenticacao autenticacao) {
        this.autenticacao = autenticacao;
    }

    @PostMapping("usuario/pessoa/autenticar")
    public String autenticarPessoa(@RequestBody String json){
        return autenticacao.autenticarPessoa(json);
    }

    @PostMapping("usuario/empresa/autenticar")
    public String autenticarEmpresa(@RequestBody String json){
        return autenticacao.autenticarEmpresa(json);
    }
}

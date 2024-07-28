package vagasOnline.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vagasOnline.api.service.GerenciadorDeCadastro;
import vagasOnline.api.usuario.Pessoa;
import vagasOnline.api.util.Conversor;

@RestController
public class CadastroController {
    GerenciadorDeCadastro gerenciadorDeCadastro;

    @Autowired
    public CadastroController(GerenciadorDeCadastro gerenciadorDeCadastro) {
        this.gerenciadorDeCadastro = gerenciadorDeCadastro;
    }

    @PostMapping("usuario/cadastrar")
    public void cadastrar(@RequestBody String json){
        gerenciadorDeCadastro.cadastrarUsuario(json);
    }

    @GetMapping("usuario/pessoa/{cpf}")
    public String informacoesPessoa(@PathVariable String cpf){
        return gerenciadorDeCadastro.informacoesPessoa(cpf);
    }

    @GetMapping("usuario/empresa/{cnpj}")
    public String informacoesEmpresa(@PathVariable String cnpj){
        return gerenciadorDeCadastro.informacoesPessoa(cnpj);
    }
}

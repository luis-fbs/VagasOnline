package vagasOnline.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vagasOnline.api.service.GerenciadorDeCadastro;
import vagasOnline.api.usuario.Pessoa;
import vagasOnline.api.util.Conversor;
import vagasOnline.api.vaga.Vaga;

import java.util.List;

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

    @GetMapping("usuario/pessoa/{index}")
    public String informacoesPessoa(@PathVariable int index){
        return gerenciadorDeCadastro.informacoesPessoa(index);
    }

    @GetMapping("usuario/empresa/{index}")
    public String informacoesEmpresa(@PathVariable int index){
        return gerenciadorDeCadastro.informacoesEmpresa(index);
    }

    @PostMapping("vaga/cadastrar")
    public Vaga cadastrarVaga(@RequestBody String json){
        return gerenciadorDeCadastro.cadastrarVaga(json);
    }
}

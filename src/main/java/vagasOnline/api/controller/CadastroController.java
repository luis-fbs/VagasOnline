package vagasOnline.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vagasOnline.api.service.GerenciadorDeCadastro;
import vagasOnline.api.usuario.Pessoa;

@RestController
//@RequestMapping("cadastro")
public class CadastroController {
    GerenciadorDeCadastro gerenciadorDeCadastro;

    @Autowired
    public CadastroController(GerenciadorDeCadastro gerenciadorDeCadastro) {
        this.gerenciadorDeCadastro = gerenciadorDeCadastro;
    }

    @PostMapping("cadastro")
    public void cadastrar(@RequestBody String info){
    }

}

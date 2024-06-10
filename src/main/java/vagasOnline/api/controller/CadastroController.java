package vagasOnline.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vagasOnline.api.service.GerenciadorDeCadastro;
import vagasOnline.api.util.Conversor;

@RestController
public class CadastroController {
    GerenciadorDeCadastro gerenciadorDeCadastro;

    @Autowired
    public CadastroController(GerenciadorDeCadastro gerenciadorDeCadastro) {
        this.gerenciadorDeCadastro = gerenciadorDeCadastro;
    }

    @PostMapping("cadastro")
    public void cadastrar(@RequestBody String json){
        gerenciadorDeCadastro.cadastrarUsuario(json);
    }
}

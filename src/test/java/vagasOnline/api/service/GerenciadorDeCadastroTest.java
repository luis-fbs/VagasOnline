package vagasOnline.api.service;

import org.junit.jupiter.api.Test;
import vagasOnline.api.usuario.Empresa;
import vagasOnline.api.usuario.Pessoa;
import vagasOnline.api.usuario.Usuario;
import vagasOnline.api.util.Conversor;
import vagasOnline.api.vaga.Vaga;

import static org.junit.jupiter.api.Assertions.*;

class GerenciadorDeCadastroTest {

    @Test
    void cadastrarUsuario() {
        GerenciadorDeCadastro gerenciadorTeste = new GerenciadorDeCadastro();
        Usuario pessoa = new Pessoa("foto", "Nome teste", "teste 123",
                "123.456.789-10", "curriculo teste");
        Usuario empresa = new Empresa("foto", "Empresa teste", "teste 123", "123.456/0007-89");

        assertEquals(false, gerenciadorTeste.usuarioExiste(pessoa));
        assertEquals(false, gerenciadorTeste.usuarioExiste(empresa));

        String jsonPessoa = """
                {"foto": "foto","nome": "Nome teste","senha": "teste 123",
                "cpf": "123.456.789-10","curriculo": "curriculo teste"}
                """;

        String jsonEmpresa = """
                {
                "foto": "foto",
                "nome": "Empresa teste",
                "senha": "teste 123",
                "cnpj": "123.456/0007-89"
                }
                """;

        gerenciadorTeste.cadastrarUsuario(jsonPessoa);
        gerenciadorTeste.cadastrarUsuario(jsonEmpresa);

        assertEquals(true, gerenciadorTeste.usuarioExiste(pessoa));
        assertEquals(true, gerenciadorTeste.usuarioExiste(empresa));
    }

    @Test
    void cadastrarVaga() {
        GerenciadorDeCadastro gerenciadorDeCadastro = new GerenciadorDeCadastro();
        Empresa empresa = new Empresa("foto", "Empresa teste", "teste 123", "123.456-789");
        Vaga vaga = new Vaga("Designer", "Fazer as artes da empresa", empresa);

        String jsonEmpresa = """
                {
                "foto": "foto",
                "nome": "Empresa teste",
                "senha": "teste 123",
                "cnpj": "123.456-789"
                }
                """;

        String jsonVaga = """
                {
                "cargo": "Designer",
                "descricao": "Fazer as artes da empresa",
                "empresa": {"cnpj": "123.456-789"}
                }
                """;

        String jsonVagaErrado = """
                {
                "cargo": "Designer",
                "descricao": "Fazer as artes da empresa"
                }
                """;

        String jsonVagaEmpresaInexistente = """
                {
                "cargo": "Designer",
                "descricao": "Fazer as artes da empresa",
                "empresa": {"cnpj": "1233"}
                }
                """;

        assertEquals(false, gerenciadorDeCadastro.vagaExiste(vaga));

        gerenciadorDeCadastro.cadastrarUsuario(jsonEmpresa);
        gerenciadorDeCadastro.cadastrarVaga(jsonVaga);

        assertEquals(true, gerenciadorDeCadastro.vagaExiste(vaga));
        assertEquals(1, gerenciadorDeCadastro.getEmpresasCadastradas().size());
        assertEquals(null, gerenciadorDeCadastro.cadastrarVaga(jsonVagaErrado));
        assertEquals(null, gerenciadorDeCadastro.cadastrarVaga(jsonVagaEmpresaInexistente));
    }
}
package vagasOnline.api.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import vagasOnline.api.dto.AutenticacaoDTO;
import vagasOnline.api.service.GerenciadorDeCadastro;
import vagasOnline.api.usuario.Empresa;
import vagasOnline.api.usuario.Pessoa;
import vagasOnline.api.usuario.Usuario;
import vagasOnline.api.vaga.Vaga;

class ConversorTest {
    @Test
    void jsonParaUsuario() {
        String jsonPessoa = """
                {
                "nome": "Luis Felipe",
                "foto": "s/f",
                "cpf": "123.456.789-10",
                "senha": "teste",
                "curriculo": "meu curriculo"
                }""";

        String jsonEmpresa = """
                {
                "nome": "Minha empresa",
                "foto": "s/f",
                "cnpj": "123.456/0007-89",
                "senha": "teste"
                }
                """;

        String jsonErrado = """
                {
                "nome": "Luis Felipe",
                "foto": "s/f",
                "Cpf": "123.456.789-10",
                "senha": "teste",
                "curriculo": "meu curriculo"
                }
                """;

        String jsonErrado2 = """
                {
                "nome": "Luis Felipe",
                "foto": "s/f",
                "senha": "teste",
                "curriculo": "meu curriculo"
                }
                """;

        String jsonErrado3 = """
                {
                "nome": "Minha empresa",
                "foto": "s/f",
                "senha": "teste"
                }
                """;

        String jsonSintaxeErrada = """
                "nome" : "Luis Felipe", senha "teste" @%#%""";

        Usuario pessoa = new Pessoa("s/f", "Luis Felipe", "teste",
                "123.456.789-10", "meu curriculo");

        Usuario empresa = new Empresa("s/f", "Minha empresa", "teste", "123.456/0007-89");

        assertEquals(true, pessoa.equals(Conversor.jsonParaUsuario(jsonPessoa)));
        assertEquals(true, empresa.equals(Conversor.jsonParaUsuario(jsonEmpresa)));
        assertEquals(null, Conversor.jsonParaUsuario(jsonErrado));
        assertEquals(null, Conversor.jsonParaUsuario(jsonErrado2));
        assertEquals(null, Conversor.jsonParaUsuario(jsonErrado3));
        assertEquals(null, Conversor.jsonParaUsuario(jsonSintaxeErrada));
    }

    @Test
    void jsonParaAutenticacaoDTO() {
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

        String jsonIdentificacaoSenha1 = """
                {
                "cpf": "123",
                "senha": "teste"
                }
                """;

        String jsonIdentificacaoSenha2 = """
                {
                "senha": "teste",
                "cnpj": "1234"
                }
                """;

        String jsonErrado = """
                {
                "teste": "1234",
                "senha": "teste"
                }
                """;

        AutenticacaoDTO pessoa = Conversor.jsonParaAutenticacaoDTO(jsonPessoa);
        AutenticacaoDTO empresa = Conversor.jsonParaAutenticacaoDTO(jsonEmpresa);
        AutenticacaoDTO idSenha1 = Conversor.jsonParaAutenticacaoDTO(jsonIdentificacaoSenha1);
        AutenticacaoDTO idSenha2 = Conversor.jsonParaAutenticacaoDTO(jsonIdentificacaoSenha2);

        assertEquals(true, pessoa.identificacao().equals("123.456.789-10") && pessoa.senha().equals("teste 123"));
        assertEquals(true, empresa.identificacao().equals("123.456/0007-89") && pessoa.senha().equals("teste 123"));
        assertEquals(true, idSenha1.identificacao().equals("123") && idSenha1.senha().equals("teste"));
        assertEquals(true, idSenha2.identificacao().equals("1234") && idSenha2.senha().equals("teste"));
        assertEquals(null, Conversor.jsonParaAutenticacaoDTO(jsonErrado));
    }

    @Test
    void jsonParaVaga() {
        Empresa empresa = new Empresa("Foto", "Empresa teste", "123", "123");
        Empresa empresa1 = new Empresa("foto", "Empresa segura", "anbiorunb234yq9gh97gh", "43211234");

        Vaga vaga = new Vaga("Gerente de projetos", "Fazer a gestao dos projetos da empresa", empresa);
        Vaga vaga1 = new Vaga("Consultor de seguranca", "Garantir que o sistema esteja seguro", empresa1);

        String jsonVaga = """
                {
                "empresa":{
                            "cnpj": "123"
                          },
                "cargo": "Gerente de projetos",
                "descricao": "Fazer a gestao dos projetos da empresa"
                }
                """;

        String jsonVaga1 = """
                {
                "descricao": "Garantir que o sistema esteja seguro",
                "cargo": "Consultor de seguranca",
                "empresa": {"cnpj": "43211234"}
                }
                """;

        String jsonVagaErrado = """
                {
                "cnpj": "123",
                "cargo": "Gerente de projetos",
                "descricao": "Fazer a gestao dos projetos da empresa"
                }
                """;

        String jsonVagaErrado1 = """
                {
                "descricao": "Garantir que o sistema esteja seguro",
                "cargo": "Consultor de seguranca",
                {"empresa": "cnpj": "43211234"}
                }
                """;

        assertEquals(true, vaga.equals(Conversor.jsonParaVaga(jsonVaga)));
        assertEquals(true, vaga1.equals(Conversor.jsonParaVaga(jsonVaga1)));
        assertEquals(null, Conversor.jsonParaVaga(jsonVagaErrado));
        assertEquals(null, Conversor.jsonParaVaga(jsonVagaErrado1));
    }
}
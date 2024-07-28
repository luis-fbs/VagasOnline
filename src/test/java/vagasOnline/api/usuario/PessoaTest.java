package vagasOnline.api.usuario;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {

    @Test
    void autenticacaoCorreta() {
        Pessoa pessoa = new Pessoa(null, null, "teste", "12345678", null);
        Pessoa pessoa1 = new Pessoa(null, null, null, null, null);

        assertEquals(true, pessoa.autenticacaoCorreta("12345678", "teste"));
        assertEquals(false, pessoa.autenticacaoCorreta("12345678", "test"));
        assertEquals(false, pessoa.autenticacaoCorreta("12345679", "teste"));
        assertEquals(false, pessoa.autenticacaoCorreta("12345679", "test"));

        assertEquals(false, pessoa1.autenticacaoCorreta(null, null));
        assertEquals(false, pessoa1.autenticacaoCorreta(null, "123"));
        assertEquals(false, pessoa1.autenticacaoCorreta("123", null));
        assertEquals(false, pessoa1.autenticacaoCorreta("123", "123"));
    }
}
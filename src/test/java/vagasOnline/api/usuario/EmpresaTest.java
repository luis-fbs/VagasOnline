package vagasOnline.api.usuario;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmpresaTest {

    @Test
    void autenticacaoCorreta() {
        Empresa empresa = new Empresa(null, null, "teste", "12345678");
        Empresa empresa1 = new Empresa(null, null, null, null);

        assertEquals(true, empresa.autenticacaoCorreta("12345678", "teste"));
        assertEquals(false, empresa.autenticacaoCorreta("12345678", "test"));
        assertEquals(false, empresa.autenticacaoCorreta("12345679", "teste"));
        assertEquals(false, empresa.autenticacaoCorreta("12345679", "test"));

        assertEquals(false, empresa1.autenticacaoCorreta(null, null));
        assertEquals(false, empresa1.autenticacaoCorreta(null, "123"));
        assertEquals(false, empresa1.autenticacaoCorreta("123", null));
        assertEquals(false, empresa1.autenticacaoCorreta("123", "123"));
    }
}
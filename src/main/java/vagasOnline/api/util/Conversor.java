package vagasOnline.api.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import vagasOnline.api.usuario.Empresa;
import vagasOnline.api.usuario.Pessoa;
import vagasOnline.api.usuario.Usuario;

public class Conversor {
    public static Usuario jsonParaUsuario(String json){
        Gson gson = new Gson();

        try {
            JsonObject parser = gson.fromJson(json, JsonObject.class);
            if (parser.has("cpf")){
                return gson.fromJson(json, Pessoa.class);
            } else if (parser.has("cnpj")) {
                return gson.fromJson(json, Empresa.class);
            } else return null;
        } catch (JsonSyntaxException exception){
            System.out.println("Sintaxe invalida\nErro: " + exception);
            return null;
        }
    }

    public static String usuarioParaJson(Usuario usuario){
        Gson gson = new Gson().newBuilder()
                              .addSerializationExclusionStrategy(new EstrategiaSerializacaoUsuario())
                              .create();

        return gson.toJson(usuario);
    }

    public static Pessoa usuarioComCpf(String cpf){
        return new Pessoa(null,null,null, cpf,null);
    }

    public static Empresa usuarioComCnpj(String cnpj) {
        return new Empresa(null, null,null, cnpj);
    }
}

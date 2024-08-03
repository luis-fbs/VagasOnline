package vagasOnline.api.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import vagasOnline.api.dto.AutenticacaoDTO;
import vagasOnline.api.usuario.Empresa;
import vagasOnline.api.usuario.Pessoa;
import vagasOnline.api.usuario.Usuario;
import vagasOnline.api.vaga.Vaga;

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
            return null;
        }
    }

    public static String usuarioParaJson(Usuario usuario){
        Gson gson = new Gson().newBuilder()
                              .addSerializationExclusionStrategy(new EstrategiaSerializacaoUsuario())
                              .create();

        return gson.toJson(usuario);
    }

    public static AutenticacaoDTO jsonParaAutenticacaoDTO(String json){
        Gson gson = new Gson();

        try {
            JsonObject parser = gson.fromJson(json, JsonObject.class);
            if (parser.has("senha")) {
                if (parser.has("cpf")) {
                    return new AutenticacaoDTO(parser.get("cpf").getAsString(), parser.get("senha").getAsString());
                } else if (parser.has("cnpj")) {
                    return new AutenticacaoDTO(parser.get("cnpj").getAsString(), parser.get("senha").getAsString());
                }
            }
            return null;
        } catch (JsonSyntaxException exception){
            return null;
        }
    }

    public static Vaga jsonParaVaga(String json){
        Gson gson = new Gson();

        try{
            return gson.fromJson(json, Vaga.class);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }
}

package vagasOnline.api.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import vagasOnline.api.usuario.Empresa;
import vagasOnline.api.usuario.Pessoa;
import vagasOnline.api.usuario.Usuario;

public class Conversor {
    public static Usuario jsonParaUsuario(String json){
        Gson gson = new Gson();
        JsonObject parser = gson.fromJson(json, JsonObject.class);

        if (parser.has("cpf")){
            return gson.fromJson(json, Pessoa.class);
        } else if (parser.has("cnpj")) {
            return gson.fromJson(json, Empresa.class);
        } else return null;
    }

}

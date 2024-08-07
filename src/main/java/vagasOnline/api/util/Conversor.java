package vagasOnline.api.util;

import com.google.gson.*;
import vagasOnline.api.dto.AutenticacaoDTO;
import vagasOnline.api.usuario.Empresa;
import vagasOnline.api.usuario.Pessoa;
import vagasOnline.api.usuario.Usuario;
import vagasOnline.api.vaga.Vaga;

import java.util.List;

public class Conversor {
    public static Usuario jsonParaUsuario(String json){
        Gson gson = new Gson();

        try {
            JsonObject parser = gson.fromJson(json, JsonObject.class);
            if (parser.has("cpf")){
                Pessoa pessoa = gson.fromJson(json, Pessoa.class);
                pessoa.inicializaListaVagasCadastradas();
                return pessoa;
            } else if (parser.has("cnpj")) {
                Empresa empresa = gson.fromJson(json, Empresa.class);
                empresa.inicializaListaVagasCadastradas();
                return empresa;
            } else return null;
        } catch (JsonSyntaxException exception){
            return null;
        }
    }

    public static String usuarioParaJson(Usuario usuario){
        Gson gson = new Gson().newBuilder()
                              .addSerializationExclusionStrategy(new EstrategiaSerializacaoUsuario())
                              .create();
        JsonObject parser = gson.fromJson(gson.toJson(usuario), JsonObject.class);
        JsonArray jsonArray = JsonParser.parseString(Conversor.vagaListParaJson(usuario.getVagasCadastradas()))
                                        .getAsJsonArray();

        parser.add("vagasCadastradas", jsonArray);
        return parser.toString();
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
            Vaga vaga = gson.fromJson(json, Vaga.class);
            if (!(vaga.getEmpresa() == null)) {
                vaga.inicializaListaCandidatos();
                return vaga;
            }
            return null;
        } catch (JsonSyntaxException exception) {
            return null;
        }
    }

    public static String vagaParaJson(Vaga vaga){
        Gson gson = new Gson().newBuilder()
                .addSerializationExclusionStrategy(new EstrategiaSerializacaoVaga())
                .create();
        JsonObject parser = gson.fromJson(gson.toJson(vaga), JsonObject.class);
        parser.addProperty("empresa", vaga.getEmpresa().getNome());

        return parser.toString();
    }

    public static String vagaListParaJson(List<Vaga> vagas){
        if (vagas != null){
            StringBuilder json = new StringBuilder("[");
            for (Vaga vaga : vagas){
                json.append(vagaParaJson(vaga)).append(',');
            }
            if (json.length() > 1) json.deleteCharAt(json.length()-1);
            json.append("]");
            return json.toString();
        }
        return null;
    }
}

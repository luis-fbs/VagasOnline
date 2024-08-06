package vagasOnline.api.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class EstrategiaSerializacaoUsuario implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return "senha".equals(fieldAttributes.getName()) ||
                "cpf".equals(fieldAttributes.getName()) ||
                "cnpj".equals(fieldAttributes.getName()) ||
                "vagasCadastradas".equals(fieldAttributes.getName());
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}

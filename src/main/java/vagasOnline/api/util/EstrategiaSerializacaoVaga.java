package vagasOnline.api.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class EstrategiaSerializacaoVaga implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return "empresa".equals(fieldAttributes.getName()) ||
                "candidatos".equals(fieldAttributes.getName());
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}

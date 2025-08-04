package hexlet.code.schemas;

import hexlet.code.schemas.checktype.MapCheckType;

import java.util.Map;
import java.util.Objects;

public class MapSchema<K, V> extends BaseSchema<Map<K, V>> {

    private Map<K, BaseSchema<V>> shapeSchemas;

    public MapSchema<K, V> required() {
        isRequired = true;
        checks.put(MapCheckType.REQUIRED.name(), Objects::nonNull);
        return this;
    }

    public MapSchema<K, V> sizeof(Integer size) {
        if (size == null) {
            throw new IllegalArgumentException("Argument cannot be null!");
        }
        if (size < 0) {
            throw new IllegalArgumentException("Argument must be a positive number!");
        }
        checks.put(MapCheckType.SIZE.name(), s -> s != null && size.equals(s.size()));
        return this;
    }

    public MapSchema<K, V> shape(Map<K, BaseSchema<V>> schemas) {
        this.shapeSchemas = schemas;
        return this;
    }

    @Override
    public boolean isValid(Map<K, V> value) {
        if (!super.isValid(value)) {
            return false;
        }
        if (shapeSchemas != null && value != null) {
            for (Map.Entry<K, BaseSchema<V>> entry : shapeSchemas.entrySet()) {
                K key = entry.getKey();
                BaseSchema<V> schema = entry.getValue();
                V fieldValue = value.get(key);
                if (!schema.isValid(fieldValue)) {
                    return false;
                }
            }
        }
        return true;
    }
}

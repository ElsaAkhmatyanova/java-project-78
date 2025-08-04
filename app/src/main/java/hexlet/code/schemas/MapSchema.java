package hexlet.code.schemas;

import hexlet.code.schemas.checktype.MapCheckType;

import java.util.Map;
import java.util.Objects;

public class MapSchema<K, V> extends BaseSchema<Map<K, V>> {

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
        if (schemas == null) {
            throw new IllegalArgumentException("Argument cannot be null!");
        }
        final Map<K, BaseSchema<V>> schemasCopy = Map.copyOf(schemas);
        checks.put(MapCheckType.SHAPE.name(), value -> {
            for (Map.Entry<K, BaseSchema<V>> entry : schemasCopy.entrySet()) {
                K key = entry.getKey();
                BaseSchema<V> schema = entry.getValue();
                V valueForKey = value.get(key);
                if (!schema.isValid(valueForKey)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}

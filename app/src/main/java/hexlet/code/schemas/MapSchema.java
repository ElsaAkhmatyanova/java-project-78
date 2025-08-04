package hexlet.code.schemas;

import hexlet.code.schemas.checktype.MapCheckType;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        isRequired = true;
        checks.put(MapCheckType.REQUIRED.name(), Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(Integer size) {
        if (size == null) {
            throw new IllegalArgumentException("Argument cannot be null!");
        }
        if (size < 0) {
            throw new IllegalArgumentException("Argument must be a positive number!");
        }
        checks.put(MapCheckType.SIZE.name(), s -> s != null && size.equals(s.size()));
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        if (schemas == null) {
            throw new IllegalArgumentException("Argument cannot be null!");
        }
        final Map<String, BaseSchema<T>> schemasCopy = Map.copyOf(schemas);
        checks.put(MapCheckType.SHAPE.name(), value -> {
            for (Map.Entry<String, BaseSchema<T>> entry : schemasCopy.entrySet()) {
                String key = entry.getKey();
                BaseSchema<T> schema = entry.getValue();
                T valueForKey = (T) value.get(key);
                if (!schema.isValid(valueForKey)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}

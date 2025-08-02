package hexlet.code.schemas;

import hexlet.code.schemas.checktype.MapCheckType;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<Map<?, ?>, MapCheckType> {

    public MapSchema() {
        super(new EnumMap<>(MapCheckType.class));
    }

    public MapSchema required() {
        isRequired = true;
        checks.put(MapCheckType.REQUIRED, Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(Integer size) {
        if (size == null) {
            throw new IllegalArgumentException("Argument cannot be null!");
        }
        if (size < 0) {
            throw new IllegalArgumentException("Argument must be a positive number!");
        }
        checks.put(MapCheckType.SIZE, s -> s != null && size.equals(s.size()));
        return this;
    }
}

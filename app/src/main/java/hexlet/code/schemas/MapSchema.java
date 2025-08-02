package hexlet.code.schemas;

import hexlet.code.schemas.checktype.MapCheckType;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    private final EnumMap<MapCheckType, Predicate<Map<?, ?>>> checks = new EnumMap<>(MapCheckType.class);

    @Override
    protected Collection<Predicate<Map<?, ?>>> getChecks() {
        return checks.values();
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

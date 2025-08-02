package hexlet.code.schemas;

import hexlet.code.schemas.checktype.NumberCheckType;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Objects;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {

    private final EnumMap<NumberCheckType, Predicate<Integer>> checks = new EnumMap<>(NumberCheckType.class);

    @Override
    protected Collection<Predicate<Integer>> getChecks() {
        return checks.values();
    }

    public NumberSchema required() {
        isRequired = true;
        checks.put(NumberCheckType.REQUIRED, Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        checks.put(NumberCheckType.POSITIVE, i -> i != null && i.compareTo(0) > 0);
        return this;
    }

    public NumberSchema range(Integer from, Integer to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Arguments cannot be null!");
        }
        checks.put(NumberCheckType.RANGE, i -> i != null && i >= from && i <= to);
        return this;
    }
}

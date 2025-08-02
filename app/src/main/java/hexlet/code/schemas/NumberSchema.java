package hexlet.code.schemas;

import hexlet.code.schemas.checktype.NumberCheckType;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class NumberSchema {
    private final Map<NumberCheckType, Predicate<Integer>> checks = new EnumMap<>(NumberCheckType.class);
    private boolean isRequired = false;

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

    public boolean isValid(Integer value) {
        if (!isRequired && value == null) {
            return true;
        }
        for (Predicate<Integer> check : checks.values()) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
}

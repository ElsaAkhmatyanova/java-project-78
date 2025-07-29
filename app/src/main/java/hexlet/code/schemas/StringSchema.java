package hexlet.code.schemas;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Predicate;

public class StringSchema {
    private final Map<CheckType, Predicate<String>> checks = new EnumMap<>(CheckType.class);
    private boolean isRequired = false;

    public StringSchema required() {
        isRequired = true;
        checks.put(CheckType.REQUIRED, s -> s != null && !s.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        checks.put(CheckType.MIN_LENGTH, s -> s != null && s.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        checks.put(CheckType.CONTAINS, s -> s != null && s.contains(substring));
        return this;
    }

    public boolean isValid(String value) {
        if (!isRequired && (value == null || value.isEmpty())) {
            return true;
        }
        for (Predicate<String> check : checks.values()) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
}

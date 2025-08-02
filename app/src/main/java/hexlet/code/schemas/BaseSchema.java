package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T, K extends Enum<K>> {

    protected final Map<K, Predicate<T>> checks;
    protected boolean isRequired = false;

    protected BaseSchema(Map<K, Predicate<T>> checks) {
        this.checks = checks;
    }

    public boolean isValid(T value) {
        if (!isRequired && value == null) {
            return true;
        }
        for (Predicate<T> check : checks.values()) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
}

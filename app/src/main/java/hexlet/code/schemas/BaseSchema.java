package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected final Map<String, Predicate<T>> checks = new HashMap<>();

    protected boolean isRequired = false;

    public boolean isValid(T value) {
        if (!isRequired && value == null) {
            return true;
        }
        for (Map.Entry<String, Predicate<T>> entry : checks.entrySet()) {
            if (!entry.getValue().test(value)) {
                return false;
            }
        }
        return true;
    }
}

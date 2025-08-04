package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected final Map<String, Predicate<T>> checks = new HashMap<>();

    protected boolean isRequired = false;

    /**
     * Run predicate validation from checks Map.
     * @param value object to check
     * @return true - if all predicate checks passed, false - if any check fail
     */
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

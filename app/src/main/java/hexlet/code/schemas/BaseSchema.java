package hexlet.code.schemas;

import java.util.Collection;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected abstract Collection<Predicate<T>> getChecks();

    protected boolean isRequired = false;

    public boolean isValid(T value) {
        if (!isRequired && value == null) {
            return true;
        }
        for (Predicate<T> check : getChecks()) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
}

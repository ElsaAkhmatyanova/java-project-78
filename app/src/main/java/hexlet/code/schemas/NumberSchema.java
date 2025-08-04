package hexlet.code.schemas;

import hexlet.code.schemas.checktype.NumberCheckType;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        isRequired = true;
        checks.put(NumberCheckType.REQUIRED.name(), Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        checks.put(NumberCheckType.POSITIVE.name(), i -> i != null && i.compareTo(0) > 0);
        return this;
    }

    public NumberSchema range(Integer from, Integer to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Arguments cannot be null!");
        }
        checks.put(NumberCheckType.RANGE.name(), i -> i != null && i >= from && i <= to);
        return this;
    }
}

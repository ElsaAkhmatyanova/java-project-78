package hexlet.code.schemas;

import hexlet.code.schemas.checktype.StringCheckType;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        isRequired = true;
        checks.put(StringCheckType.REQUIRED.name(), s -> s != null && !s.isEmpty());
        return this;
    }

    public StringSchema minLength(Integer length) {
        if (length == null) {
            throw new IllegalArgumentException("Argument cannot be null!");
        }
        if (length < 0) {
            throw new IllegalArgumentException("Argument must be a positive number!");
        }
        checks.put(StringCheckType.MIN_LENGTH.name(), s -> s != null && s.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        if (substring == null) {
            throw new IllegalArgumentException("Argument cannot be null!");
        }
        checks.put(StringCheckType.CONTAINS.name(), s -> s != null && s.contains(substring));
        return this;
    }
}

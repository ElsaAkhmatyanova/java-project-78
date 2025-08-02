package hexlet.code.schemas;

import hexlet.code.schemas.checktype.StringCheckType;

import java.util.EnumMap;

public class StringSchema extends BaseSchema<String, StringCheckType> {

    public StringSchema() {
        super(new EnumMap<>(StringCheckType.class));
    }

    public StringSchema required() {
        isRequired = true;
        checks.put(StringCheckType.REQUIRED, s -> s != null && !s.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        checks.put(StringCheckType.MIN_LENGTH, s -> s != null && s.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        checks.put(StringCheckType.CONTAINS, s -> s != null && s.contains(substring));
        return this;
    }
}

### Hexlet tests and linter status:

[![Actions Status](https://github.com/ElsaAkhmatyanova/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/ElsaAkhmatyanova/java-project-78/actions)
![CI](https://github.com/ElsaAkhmatyanova/java-project-78/actions/workflows/ci.yml/badge.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ElsaAkhmatyanova_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=ElsaAkhmatyanova_java-project-78)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ElsaAkhmatyanova_java-project-78&metric=coverage)](https://sonarcloud.io/summary/new_code?id=ElsaAkhmatyanova_java-project-78)

# Data validator
Валидатор данных – библиотека, с помощью которой можно проверять корректность данных.

## Поддерживаемые типы данных
- String
- Integer
- Map

## Доступные проверки
### String
- required() — делает данные обязательными для заполнения. Иными словами добавляет в схему ограничение, которое не позволяет использовать null или пустую строку в качестве значения
- minLength() — добавляет в схему ограничение минимальной длины для строки. Строка должна быть равна или длиннее указанного числа
- contains() — добавляет в схему ограничение по содержимому строки. Строка должна содержать определённую подстроку
### Integer
- required() — добавляет в схему ограничение, которое не позволяет использовать null в качестве значения
- positive() — добавляет ограничение на знак числа. Число должно быть положительным
- range() — добавляет допустимый диапазон, в который должно попадать значение числа включая границы
### Map
- required() — добавляет в схему ограничение, которое не позволяет использовать null в качестве значения. Требуется тип данных Map
- sizeof() — добавляет ограничение на размер мапы. Количество пар ключ-значений в объекте Map должно быть равно заданному
- shape() — используется для определения свойств объекта Map и создания схемы для валидации их значений. Каждому свойству объекта Map привязывается свой набор ограничений (своя схема), что позволяет более точно контролировать данные

## Пример использования
```java
// string schema
var v = new Validator();
var schema1 = v.string();
schema1.isValid(null); // true
schema1.required();
schema1.isValid(null); // false
schema1.isValid("what does the fox say"); // true
schema1.contains("wh").isValid("what does the fox say"); // true
schema1.isValid("what does the fox say"); // false

var schema2 = v.string();
schema2.minLength(10).minLength(4).isValid("Hexlet"); // true

// number schema
var schema3 = v.number();
schema3.isValid(5); // true
schema3.positive().isValid(-10); // false
schema3.range(5, 10);
schema3.isValid(5); // true
schema3.isValid(4); // false

// map schema
var schema4 = v.map();
schema4.isValid(null); // true
schema4.required();
schema4.isValid(null); // false
schema4.isValid(new HashMap<>()); // true
var data = new HashMap<String, String>();
data.put("key1", "value1");
schema4.isValid(data); // true
schema4.sizeof(2);
schema4.isValid(data);  // false
data.put("key2", "value2");
schema4.isValid(data); // true

var schema5 = v.map();
Map<String, BaseSchema<String>> schemas = new HashMap<>();
schemas.put("firstName", v.string().required());
schemas.put("lastName", v.string().required().minLength(2));
schema5.shape(schemas);

Map<String, String> human1 = new HashMap<>();
human1.put("firstName", "John");
human1.put("lastName", "Smith");
schema5.isValid(human1); // true

Map<String, String> human2 = new HashMap<>();
human2.put("firstName", "John");
human2.put("lastName", null);
schema5.isValid(human2); // false

Map<String, String> human3 = new HashMap<>();
human3.put("firstName", "Anna");
human3.put("lastName", "B");
schema5.isValid(human3); // false
```



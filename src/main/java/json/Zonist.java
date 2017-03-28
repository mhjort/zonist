package json;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Zonist {
    private static String removeChar(String s, char c) {
        return s.codePoints().filter(x -> x != c)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static Object createValue(String val) {
        String trimmed = val.trim();
        if (trimmed.substring(0, 1).equals("\"")) {
            return removeChar(trimmed, '"');
        } else {
            return Integer.valueOf(removeChar(trimmed, '"'));
        }
    }

    public static Map parse(String json) {
        String contents = json.substring(1, json.length() - 1);
        return Stream.of(contents.split(",")).
                map(i -> i.split(":")).
                collect(Collectors.toMap(i -> removeChar(i[0].trim(), '"'), i -> createValue(i[1])));
    }

    public static Object emitValue(Object val) {
        if (val instanceof Integer) {
            return val.toString();
        } else {
            return "\"" + val + "\"";
        }
    }

    public static String unparse(Map<String, Object> json) {
        String contents = json.entrySet().stream().
                map((Map.Entry<String, Object> e) -> emitValue(e.getKey()) + ": " + emitValue(e.getValue())).
                collect(Collectors.joining(","));
        return "{" + contents + "}";
    }

    public static void main(String[] args) {
        String json = "{\"foo\": 1, \"bar\": 2,   \"trolo\": \"bolo\"}";
        //System.out.println(removeChar("foodd", 'o'));
        //System.out.println("foo\"".replace("\"", ""));

        parse(json).keySet().forEach(i -> System.out.println(i));
        parse(json).values().forEach(i -> System.out.println(i));

        final Map expected = new HashMap();
        expected.put("foo", 1);
        expected.put("bar", 2);
        expected.put("trolo", "bolo");
        System.out.println(unparse(expected));
    }
}

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

    public static void main(String[] args) {
        String json = "{\"foo\": 1, \"bar\": 2,   \"trolo\": \"bolo\"}";
        //System.out.println(removeChar("foodd", 'o'));
        //System.out.println("foo\"".replace("\"", ""));

        parse(json).keySet().forEach(i -> System.out.println(i));
        parse(json).values().forEach(i -> System.out.println(i));
    }
}

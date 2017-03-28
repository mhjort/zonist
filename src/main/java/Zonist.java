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

    public static Map parse(String json) {
        String contents = json.substring(1, json.length() - 1);
        return Stream.of(contents.split(","))
                .parallel().map(i -> removeChar(i.trim(), '"')).
                map(i -> i.split(":")).
                collect(Collectors.toMap(i -> i[0], i -> i[1]));
    }

    public static void main(String[] args) {
        String json = "{\"foo\": 1, \"bar\": 2,   \"trolo\": \"bolo\"}";
        //System.out.println(removeChar("foodd", 'o'));
        //System.out.println("foo\"".replace("\"", ""));

        parse(json).keySet().forEach(i -> System.out.println(i));
        parse(json).values().forEach(i -> System.out.println(i));
    }
}

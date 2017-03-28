package json;

import json.Zonist;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ZonistTest {
    @Test
    public void parsesJsonWithDepthOne() {
       String json = "{\"foo\": 1, \"bar\": 2,   \"trolo\": \"bolo\"}";

        final Map expected = new HashMap();
        expected.put("foo", 1);
        expected.put("bar", 2);
        expected.put("trolo", "bolo");
        assertThat(Zonist.parse(json), is(equalTo(expected)));
    }

    @Test
    public void unparsesMapWithDepthOneToJson() {
        String json = "{\"foo\": 1,\"trolo\": \"bolo\"}";

        final Map map = new HashMap();
        map.put("foo", 1);
        map.put("trolo", "bolo");
        assertThat(Zonist.unparse(map), is(equalTo(json)));
    }
}

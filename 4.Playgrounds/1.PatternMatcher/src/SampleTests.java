import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SampleTests {
    @Test
    public void test1() {
        assertFalse(PatternMatcher.match("abcdef", "a?cd*g"));
    }

    @Test
    public void test2() {
        assertTrue(PatternMatcher.match("abcdef", "*ef"));
    }

    @Test
    public void test3() {
        assertTrue(PatternMatcher.match("abcdef", "d?"));
    }

    @Test
    public void test4() {
        assertTrue(PatternMatcher.match("abcdef", "de"));
    }
}

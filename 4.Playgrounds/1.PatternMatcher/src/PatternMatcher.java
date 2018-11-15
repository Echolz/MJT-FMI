import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PatternMatcher {
    public static boolean match(String s, String p) {
        p = p.replaceAll("[?]", ".");
        p = p.replaceAll("[*]", ".*");

        StringBuilder sb = new StringBuilder();

        sb.append(".*");
        sb.append(p);
        sb.append(".*");

        System.out.println("".matches("[.|]"));

        return s.matches(sb.toString());
    }
}

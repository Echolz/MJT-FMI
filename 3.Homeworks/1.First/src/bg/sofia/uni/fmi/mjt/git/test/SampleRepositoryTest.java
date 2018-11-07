package bg.sofia.uni.fmi.mjt.git.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import bg.sofia.uni.fmi.mjt.git.Repository;
import bg.sofia.uni.fmi.mjt.git.Result;
import bg.sofia.uni.fmi.mjt.git.utils.DateFormatter;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class SampleRepositoryTest {

    private Repository repo;

    @Before
    public void setUp() {
        repo = new Repository();
    }

    @Test
    public void testAdd_MultipleFiles() {
        Result actual = repo.add("foo.txt", "bar.txt", "baz.txt");
        assertSuccess("added foo.txt, bar.txt, baz.txt to stage", actual);
    }

    @Test
    public void testRemove_DoesNothingWhenAnyFileIsMissing() {
        repo.add("foo.txt", "bar.txt");

        Result actual = repo.remove("foo.txt", "baz.txt");
        assertFail("'baz.txt' did not match any files", actual);

//        actual = repo.commit("After removal");
//        assertSuccess("2 files changed", actual);
    }

    @Test
    public void testDateFormat_GeneratesProperStringFromDate() {
        LocalDateTime date = LocalDateTime.of(2000, 12, 12, 12, 12);

        assertEquals("12 12 12:12 2000", DateFormatter.formatDate(date));
    }

    @Test
    public void testCheckoutBranch_CanSwitchBranches() {
        repo.add("src/Main.java");
        repo.commit("Add Main.java");

        repo.createBranch("dev");
        Result actual = repo.checkoutBranch("dev");
        assertSuccess("switched to branch dev", actual);

        repo.remove("src/Main.java");
        repo.commit("Remove Main.java");
        assertEquals("Remove Main.java", repo.getHead().getMessage());

        actual = repo.checkoutBranch("master");
        assertSuccess("switched to branch master", actual);
        assertEquals("Add Main.java", repo.getHead().getMessage());
    }

    private static void assertFail(String expected, Result actual) {
        assertFalse(actual.isSuccessful());
        assertEquals(expected, actual.getMessage());
    }

    private static void assertSuccess(String expected, Result actual) {
        assertTrue(actual.isSuccessful());
        assertEquals(expected, actual.getMessage());
    }
}
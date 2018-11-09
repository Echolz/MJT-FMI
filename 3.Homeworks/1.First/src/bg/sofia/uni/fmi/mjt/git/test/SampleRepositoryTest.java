package bg.sofia.uni.fmi.mjt.git.test;

import bg.sofia.uni.fmi.mjt.git.Repository;
import bg.sofia.uni.fmi.mjt.git.Result;
import bg.sofia.uni.fmi.mjt.git.utils.DateFormatter;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

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
    }

    @Test
    public void testGetHead_ReturnsNullWhenNoCommitsAreDone() {
        assertNull(repo.getHead());
    }

    @Test
    public void testCommit_CorrectMessageAfterAddingFilesAndDeletingNotExistingFiles() {
        repo.add("foo.txt", "bar.txt");

        Result actual = repo.remove("foo.txt", "baz.txt");
        assertFail("'baz.txt' did not match any files", actual);

        actual = repo.commit("After removal");
        assertSuccess("2 files changed", actual);
    }

    @Test
    public void testCreateBranch_CorrectMessageWhenTryingToCreateMaster() {
        Result actual = repo.createBranch("master");

        assertFail("branch master already exists", actual);
    }

    @Test
    public void testCreateBranch_CorrectMessageWhenCreatingANewBranch() {
        Result actual = repo.createBranch("new branch");

        assertSuccess("created branch new branch", actual);
    }

    @Test
    public void testLog_GivesProperMessageWhenNoCommitsAreDoneToMaster() {
        Result actual = repo.log();

        assertFail("master does not have any commits yet", actual);
    }

    @Test
    public void testGetBranch_ReturnsMasterAfterCreationOfRepository() {
        assertEquals("master", repo.getBranch());
    }

    @Test
    public void testCheckout_ReturnsProperMessageWhenBranchDoesNotExist() {
        assertFail("branch wow does not exist", repo.checkoutBranch("wow"));
    }

    @Test
    public void testCheckout_ReturnsProperMessageWhenBranchExists() {
        repo.createBranch("new branch");
        
        Result actual = repo.checkoutBranch("new branch");

        assertSuccess("switched to branch new branch", actual);
    }

    @Test
    public void testCheckoutBranch_CanSwitchBranches() {
        repo.add("src/Main.java");
        repo.commit("Add Main.java");

        assertEquals("Add Main.java", repo.getHead().getMessage());

        repo.createBranch("dev");
        Result actual = repo.checkoutBranch("dev");
        assertSuccess("switched to branch dev", actual);

        repo.remove("src/Main.java");
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
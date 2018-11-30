package bg.sofia.uni.fmi.mjt.movies;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class SampleMoviesExplorerTest {

    @BeforeClass
    public static void setUp() throws IOException {
        String movies = "FMI All Stars (2018)/Semerdzhiev, Atanas/Georgiev, Kalin" + System.lineSeparator()
                + "Modern Java Technologies (2019)/Gosling, James/Johansson, Scala/Duke" + System.lineSeparator()
                + "FMI All Stars (2018)/Semerdzhiev, Atanas/Georgiev, Kalin" + System.lineSeparator()
                + "Modern Java Technologies (2019)/Gosling, James/Johansson, Scala/Duke" + System.lineSeparator()
                + "FMI All Stars (2018)/Semerdzhiev, Atanas/Georgiev, Kalin" + System.lineSeparator()
                + "Modern Java Technologies (2019)/Johansson, Scala/Duke" + System.lineSeparator()
                + "FMI All Stars (2018)/Semerdzhiev, Atanas" + System.lineSeparator()
                + "Modern Java Technologies (2019)/Gosling, James" + System.lineSeparator()
                + "FMI All Stars (2018)/Semerdzhiev, Atanas/Georgiev, Kalin" + System.lineSeparator()
                + "Modern Java Technologies (2019)/Johansson, Scala" + System.lineSeparator()
                + "FMI All Stars (2018)/Georgiev, Kalin" + System.lineSeparator()
                + "Modern Java Technologies (2019)/Gosling" + System.lineSeparator();
        MoviesExplorer moviesExplorer = new MoviesExplorer(new ByteArrayInputStream(movies.getBytes()));
    }

    @Test
    public void testDefaultStyleCheckerIfImportWithWildcardProducesError() {

    }
}
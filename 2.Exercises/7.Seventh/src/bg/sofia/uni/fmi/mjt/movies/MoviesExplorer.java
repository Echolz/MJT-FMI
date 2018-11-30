package bg.sofia.uni.fmi.mjt.movies;

import bg.sofia.uni.fmi.mjt.movies.model.Actor;
import bg.sofia.uni.fmi.mjt.movies.model.Movie;
import bg.sofia.uni.fmi.mjt.movies.utils.StreamParser;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class MoviesExplorer {
    private Collection<Movie> allMovies;

    public MoviesExplorer(InputStream dataInput) {
        List<String> movieLines = StreamParser.parse(dataInput);
        allMovies = movieLines.stream()
                .map(Movie::createMovie)
                .collect(Collectors.toList());
    }

    public Collection<Movie> getMovies() {
        return new ArrayList<>(allMovies);
    }

    public int countMoviesReleasedIn(int year) {
        return (int) allMovies.stream()
                .filter(movie -> movie.getYear() == year)
                .count();
    }

    public Movie findFirstMovieWithTitle(String title) {
        return allMovies.stream()
                .filter(movie -> movie.getTitle().equals(title))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public Collection<Actor> getAllActors() {
        Collection<Actor> actors = new HashSet<>();
        allMovies.forEach(m -> actors.addAll(m.getActors()));
        return actors;
    }

    public int getFirstYear() {
        return allMovies.stream()
                .mapToInt(Movie::getYear)
                .min()
                .orElse(-1);
    }

    public Collection<Movie> getAllMoviesBy(Actor actor) {
        return allMovies.stream()
                .filter(m -> m.getActors().contains(actor))
                .collect(Collectors.toList());
    }

    public Collection<Movie> getMoviesSortedByReleaseYear() {
        return allMovies.stream()
                .sorted(Comparator.comparingInt(Movie::getYear))
                .collect(Collectors.toList());
    }

    public int findYearWithLeastNumberOfReleasedMovies() {
        Map<Integer, List<Movie>> map = allMovies.stream().collect(groupingBy(Movie::getYear));
        return map.keySet().stream()
                .min(Comparator.comparingInt(k -> map.get(k).size()))
                .orElse(-1);
    }

    public Movie findMovieWithGreatestNumberOfActors() {
        return allMovies.stream().max(Comparator.comparing(m1 -> m1.getActors().size())).orElse(null);
    }
}

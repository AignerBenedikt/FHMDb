package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeControllerTest {

    private HomeController controller;

    @BeforeEach
    public void setUp() {
        controller = new HomeController();
        controller.allMovies = Arrays.asList(
                new Movie("The Matrix", "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.", Arrays.asList("Action", "Sci-Fi")),
                new Movie("Inception", "A thief who steals corporate secrets through dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.", Arrays.asList("Action", "Thriller")),
                new Movie("The Godfather", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", Arrays.asList("Crime", "Drama")),
                new Movie("Shrek", "A mean lord exiles fairytale creatures to the swamp of a grumpy ogre, who must go on a quest and rescue a princess for the lord in order to get his land back.", Arrays.asList("Animation", "Comedy")),
                new Movie("Toy Story", "A cowboy doll is profoundly threatened and jealous when a new spaceman figure supplants him as top toy in a boy's room.", Arrays.asList("Animation", "Family"))
        );
        controller.observableMovies = FXCollections.observableArrayList(controller.allMovies);
        controller.genreComboBox = new JFXComboBox<>();
        controller.searchField = new TextField();
        controller.sortBtn = new JFXButton();
        controller.filterBtn = new JFXButton();
        controller.searchBtn = new JFXButton();
    }

    @Test
    public void testFilterMoviesByGenre() {
        controller.genreComboBox.setValue("Action");
        controller.applyFilter();
        assertTrue(controller.observableMovies.stream().allMatch(movie -> movie.getGenres().contains("Action")), "Filme sollten nach Genre 'Action' gefiltert werden.");
    }

    @Test
    public void testSortMoviesAscending() {
        controller.sortBtn.setText("Sort (asc)");
        controller.toggleSort();
        List<String> titles = controller.observableMovies.stream().map(Movie::getTitle).collect(Collectors.toList());
        assertEquals(Arrays.asList("Inception", "Shrek", "The Godfather", "The Matrix", "Toy Story"), titles, "Filme sollten aufsteigend sortiert werden.");
    }

    @Test
    public void testSortMoviesDescending() {
        controller.sortBtn.setText("Sort (desc)");
        controller.toggleSort();
        List<String> titles = controller.observableMovies.stream().map(Movie::getTitle).collect(Collectors.toList());
        assertEquals(Arrays.asList("Toy Story", "The Matrix", "The Godfather", "Shrek", "Inception"), titles, "Filme sollten absteigend sortiert werden.");
    }

    @Test
    public void testApplySearch() {
        controller.searchField.setText("The");
        controller.applySearch();
        assertTrue(controller.observableMovies.stream().allMatch(movie -> movie.getTitle().contains("The")), "Suche sollte nur Filme mit 'The' im Titel zurückgeben.");
    }

    @Test
    public void testFilterAndSearchCombined() {
        controller.genreComboBox.setValue("Animation");
        controller.applyFilter();
        controller.searchField.setText("Shrek");
        controller.applySearch();
        assertEquals(1, controller.observableMovies.size(), "Es sollte nur ein Film übrig bleiben, der sowohl dem Genre 'Animation' entspricht als auch 'Shrek' im Titel hat.");
    }

    @Test
    public void testSearchCaseInsensitive() {
        controller.searchField.setText("shrek");
        controller.applySearch();
        assertTrue(controller.observableMovies.stream().anyMatch(movie -> movie.getTitle().equalsIgnoreCase("Shrek")), "Suche sollte case-insensitive sein.");
    }

    @Test
    public void testEmptySearchFieldReturnsAllMovies() {
        controller.searchField.setText("");
        controller.applySearch();
        assertEquals(controller.allMovies.size(), controller.observableMovies.size(), "Leeres Suchfeld sollte alle Filme zurückgeben.");
    }

    @Test
    public void testEmptyGenreFilterReturnsAllMovies() {
        controller.genreComboBox.setValue("");
        controller.applyFilter();
        assertEquals(controller.allMovies.size(), controller.observableMovies.size(), "Leerer Genre-Filter sollte alle Filme zurückgeben.");
    }

    @Test
    public void testGenreComboBoxIsPopulated() {
        assertEquals(Arrays.asList("Action", "Sci-Fi", "Thriller", "Crime", "Drama", "Animation", "Comedy", "Family"), controller.genreComboBox.getItems().stream().sorted().collect(Collectors.toList()), "Genre-ComboBox sollte korrekt mit Genres befüllt sein.");
    }
}

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class HomeControllerTest {
    private HomeController homeController;

    // Initializes the testing environment by setting up the stage and UI components.
    @Start
    private void init(Stage stage) {
        homeController = new HomeController();
        VBox root = new VBox(homeController.movieListView = new JFXListView<>());
        stage.setScene(new Scene(root));
        stage.show();
        Platform.runLater(() -> homeController.setupComponents(new TextField(), new JFXComboBox<>(), new JFXButton()));
    }

    // Prepares the data for each test.
    @BeforeEach
    public void prepareData() {
        Platform.runLater(() -> homeController.observableMovieList = FXCollections.observableArrayList(
                new Movie("Comedy Night", "A night full of laughter", Collections.singletonList("Comedy")),
                new Movie("Adventure Awaits", "Explore the unknown", Collections.singletonList("Adventure"))
        ));
    }

    // Tests if movies are ordered in ascending order correctly.
    @Test
    public void whenOrderedAsc_thenFirstMovieIsAdventureAwaits() {
        Platform.runLater(() -> {
            homeController.orderMovies(true);
            assertEquals("Adventure Awaits", homeController.observableMovieList.get(0).getTitle(), "First movie should be 'Adventure Awaits' when ordered ascending.");
        });
    }
    // Tests if movies are ordered in descending order correctly.
    @Test
    public void whenOrderedDesc_thenFirstMovieIsComedyNight() {
        Platform.runLater(() -> {
            homeController.orderMovies(false);
            assertEquals("Comedy Night", homeController.observableMovieList.get(0).getTitle(), "First movie should be 'Comedy Night' when ordered descending.");
        });
    }

    // Tests if filtering by title works correctly.
    @Test
    public void ensureFilteringWorksForGivenTitle() {
        Platform.runLater(() -> {
            homeController.applyTitleFilter("Adventure");
            assertTrue(homeController.observableMovieList.stream().noneMatch(movie -> movie.getTitle().equals("Comedy Night")), "List should not contain 'Comedy Night' after filtering.");
        });
    }

    private class HomeController {
        JFXListView<Movie> movieListView;
        ObservableList<Movie> observableMovieList;

        // Sets up components and their event handlers.
        void setupComponents(TextField searchField, JFXComboBox<String> genreComboBox, JFXButton sortButton) {
            sortButton.setOnAction(e -> orderMovies(true)); // Example: Sorts ascending on click
            searchField.textProperty().addListener((obs, oldText, newText) -> applyTitleFilter(newText));
        }

        // Orders movies either ascending or descending.
        void orderMovies(boolean asc) {
            if (asc) {
                FXCollections.sort(observableMovieList, (movie1, movie2) -> movie1.getTitle().compareTo(movie2.getTitle()));
            } else {
                FXCollections.sort(observableMovieList, (movie1, movie2) -> movie2.getTitle().compareTo(movie1.getTitle()));
            }
            movieListView.setItems(observableMovieList); // Updates the ListView
        }

        // Filters the movies based on a given title.
        void applyTitleFilter(String title) {
            if (title == null || title.isEmpty()) {
                observableMovieList.setAll(allMovies); // No filter, show all movies
            } else {
                List<Movie> filteredList = allMovies.stream()
                        .filter(movie -> movie.getTitle().toLowerCase().contains(title.toLowerCase()))
                        .collect(Collectors.toList());
                observableMovieList.setAll(filteredList);
            }
            movieListView.setItems(observableMovieList); // Updates the ListView
        }

        // Placeholder for the Movie class
        private class Movie {
            private final String title;
            private final String description;
            private final List<String> genres;

            Movie(String title, String description, List<String> genres) {
                this.title = title;
                this.description = description;
                this.genres = genres;
            }

            String getTitle() {
                return title;
            }


    }
}

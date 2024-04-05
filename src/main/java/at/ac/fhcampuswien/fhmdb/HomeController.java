package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView<Movie> movieListView;

    @FXML
    public JFXComboBox<String> genreComboBox;

    @FXML
    public JFXButton sortBtn;

    @FXML
    public JFXButton filterBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.setAll(allMovies);         // add dummy data to observable list
        observableMovies.sort(Comparator.comparing(Movie::getTitle));
        sortBtn.setText("Sort (desc)");

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        genreComboBox.getItems().add("No Genre");
        List<String> genres = allMovies.stream().flatMap(movie -> movie.getGenres().stream()).distinct().collect(Collectors.toList());
        genreComboBox.getItems().addAll(genres);
        genreComboBox.setValue("No Genre");

        sortBtn.setOnAction(actionEvent -> toggleSort());
        filterBtn.setOnAction(actionEvent -> applyCombinedFilter());

        searchField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                applyCombinedFilter();
            }
        });
    }

    void toggleSort() {
        if ("Sort (asc)".equals(sortBtn.getText())) {
            observableMovies.sort(Comparator.comparing(Movie::getTitle));
            sortBtn.setText("Sort (desc)");
        } else {
            observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
            sortBtn.setText("Sort (asc)");
        }
    }

    void applyCombinedFilter(){
        String searchText = searchField.getText().trim().toLowerCase();
        String selectedGenre = genreComboBox.getValue().equals("No Genre") ? null : genreComboBox.getValue();

        Stream<Movie> filteredStream = allMovies.stream().filter(movie -> selectedGenre == null || movie.getGenres().contains(selectedGenre))
                .filter(movie -> selectedGenre == null || movie.getGenres().contains(selectedGenre))
                .filter(movie -> searchText.isEmpty() || movie.getTitle().toLowerCase().contains(searchText));
        List<Movie> filteredMovies = filteredStream.collect(Collectors.toList());
        observableMovies.setAll(filteredMovies);
    }

    private void filterMovies(String genre, String searchText) {
        Stream<Movie> filteredStream = allMovies.stream().filter(movie -> genre == null || movie.getGenres().contains(genre));

        if (searchText != null && !searchText.isEmpty()) {
            filteredStream = filteredStream.filter(movie -> movie.getTitle().toLowerCase().contains(searchText.toLowerCase()));
        }

        List<Movie> filteredMovies = filteredStream.collect(Collectors.toList());
        observableMovies.setAll(filteredMovies);
        movieListView.setItems(observableMovies);
    }
}
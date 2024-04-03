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

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        List<String> genres = allMovies.stream().flatMap(movie -> movie.getGenres().stream()).distinct().collect(Collectors.toList());
        genreComboBox.getItems().addAll(genres);
        genreComboBox.setPromptText("Filter by Genre");

        sortBtn.setOnAction(actionEvent -> toggleSort());

        filterBtn.setOnAction(actionEvent -> applyFilter());

        searchBtn.setOnAction(actionEvent -> applySearch());

        searchField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                applySearch();
            }
        });

    }

    void toggleSort() {
        if (sortBtn.getText().equals("Sort (asc)")) {
            observableMovies.sort(Comparator.comparing(Movie::getTitle));
            sortBtn.setText("Sort (desc)");
        } else {
            observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
            sortBtn.setText("Sort (asc)");
        }
    }

    void applyFilter() {
        String selectedGenre = genreComboBox.getValue();
        //searchField.clear();
        filterMovies(selectedGenre, null);
    }

    void applySearch() {
        String searchText = searchField.getText();
        //genreComboBox.getSelectionModel().clearSelection();
        filterMovies(null, searchText);
    }

    private void filterMovies(String genre, String searchText) {
        Stream<Movie> filteredStream = allMovies.stream();

        if (genre != null && !genre.isEmpty()) {
            filteredStream = filteredStream.filter(movie -> movie.getGenres().contains(genre));
        }
        if (searchText != null && !searchText.trim().isEmpty()) {
            String lowerCaseSearchText = searchText.toLowerCase();
            filteredStream = filteredStream.filter(movie -> movie.getTitle().toLowerCase().contains(lowerCaseSearchText));
        }

        List<Movie> filteredMovies = filteredStream.collect(Collectors.toList());
        observableMovies.setAll(filteredMovies);
    }
}
package at.ac.fhcampuswien.fhmdb;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    public void testSearchMoviesByTitleAndDescription() {
        try {
            // Zugriff auf die Methode searchMoviesByTitleAndDescription, die zwei Strings akzeptiert
            Method m = Movie.class.getMethod("searchMoviesByTitleAndDescription", String.class, String.class);

            // Erstellen einer Instanz von MovieManager
            MovieManager movieManager = new MovieManager();

            // Prüft, ob die Methode mit breiten Suchkriterien funktioniert
            testWithVariousInputs(movieManager, m, "adventure", "epic", true); // Sollte Treffer finden
            testWithVariousInputs(movieManager, m, "", "", true); // Sollte viele Treffer finden, da keine spezifischen Kriterien
            testWithVariousInputs(movieManager, m, "xyz123", "abc987", false); // Sollte keine Treffer finden

        } catch (NoSuchMethodException nsme) {
            nsme.printStackTrace();
            fail("There should be a method called searchMoviesByTitleAndDescription in the MovieManager class.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Problems might have occurred invoking the method. Also check return types and parameters.");
        }
    }

    private void testWithVariousInputs(MovieManager movieManager, Method m, String title, String description, boolean expectResults) throws Exception {
        @SuppressWarnings("unchecked")
        List<Movie> filteredMovies = (List<Movie>) m.invoke(movieManager, title, description);
        if(expectResults) {
            assertTrue(!filteredMovies.isEmpty(), "Sollte Treffer für '" + title + "' und '" + description + "' finden.");
        } else {
            assertTrue(filteredMovies.isEmpty(), "Sollte keine Treffer für '" + title + "' und '" + description + "' finden.");
        }
    }
}

}
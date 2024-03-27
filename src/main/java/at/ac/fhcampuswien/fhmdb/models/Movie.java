package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    // TODO add more properties here
    private List<String> genres;

    public Movie(String title, String description, List<String> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getGenres() {
        return genres;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Shawshank Redemption","Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion.", Arrays.asList("DRAMA")));
        movies.add(new Movie("The Godfather","The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",Arrays.asList("CRIME", "DRAMA")));
        movies.add(new Movie("The Dark Knight","When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",Arrays.asList("ACTION", "CRIME", "DRAMA", "THRILLER")));
        movies.add(new Movie("The Godfather Part II","The early life and career of Vito Corleone in 1920s New York City is portrayed, while his son, Michael, expands and tightens his grip on the family crime syndicate.",Arrays.asList("CRIME", "DRAMA")));
        movies.add(new Movie("12 Angry Men","The jury in a New York City murder trial is frustrated by a single member whose skeptical caution forces them to more carefully consider the evidence before jumping to a hasty verdict.",Arrays.asList("CRIME", "DRAMA")));
        movies.add(new Movie("Schindler's List","In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.",Arrays.asList("BIOGRAPHY", "DRAMA", "HISTORY")));
        movies.add(new Movie("The Lord of the Rings: The Return of the King","Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.",Arrays.asList("ACTION", "ADVENTURE", "DRAMA", "FANTASY")));
        movies.add(new Movie("Pulp Fiction","The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",Arrays.asList("CRIME", "DRAMA")));
        movies.add(new Movie("The Lord of the Rings: The Fellowship of the Ring","A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.",Arrays.asList("ACTION", "ADVENTURE", "DRAMA", "FANTASY")));
        movies.add(new Movie("The Good, the Bad and the Ugly","A bounty hunting scam joins two men in an uneasy alliance against a third in a race to find a fortune in gold buried in a remote cemetery.",Arrays.asList("ADVENTURE", "WESTERN")));
        movies.add(new Movie("Forrest Gump","The history of the United States from the 1950s to the '70s unfolds from the perspective of an Alabama man with an IQ of 75, who yearns to be reunited with his childhood sweetheart.",Arrays.asList("DRAMA", "ROMANCE")));
        movies.add(new Movie("The Lord of the Rings: The Two Towers","While Frodo and Sam edge closer to Mordor with the help of the shifty Gollum, the divided fellowship makes a stand against Sauron's new ally, Saruman, and his hordes of Isengard.",Arrays.asList("ACTION", "ADVENTURE", "DRAMA", "FANTASY")));
        movies.add(new Movie("Fight Club","An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.",Arrays.asList("DRAMA")));
        movies.add(new Movie("Inception","A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.",Arrays.asList("ACTION", "ADVENTURE", "SCI-FI", "THRILLER")));
        movies.add(new Movie("Dune: Part Two","Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.",Arrays.asList("ACTION", "ADVENTURE", "DRAMA", "SCI-FI")));
        movies.add(new Movie("Star Wars: Episode V - The Empire Strikes Back","After the Rebels are overpowered by the Empire, Luke Skywalker begins his Jedi training with Yoda, while his friends are pursued across the galaxy by Darth Vader and bounty hunter Boba Fett.",Arrays.asList("ACTION", "ADVENTURE", "FANTASY", "SCI-FI")));
        movies.add(new Movie("The Matrix","When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.",Arrays.asList("ACTION", "SCI-FI")));
        movies.add(new Movie("Goodfellas","The story of Henry Hill and his life in the mafia, covering his relationship with his wife Karen and his mob partners Jimmy Conway and Tommy DeVito.",Arrays.asList("BIOGRAPHY", "CRIME", "DRAMA")));
        movies.add(new Movie("One Flew Over the Cuckoo's Nest","In the Fall of 1963, a Korean War veteran and criminal pleads insanity and is admitted to a mental institution, where he rallies up the scared patients against the tyrannical nurse.",Arrays.asList("DRAMA")));
        movies.add(new Movie("Se7en","Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his motives.",Arrays.asList("CRIME", "DRAMA", "MYSTERY", "THRILLER")));
        movies.add(new Movie("Interstellar","When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.",Arrays.asList("ADVENTURE", "DRAMA", "SCI-FI")));

        return movies;
    }
}

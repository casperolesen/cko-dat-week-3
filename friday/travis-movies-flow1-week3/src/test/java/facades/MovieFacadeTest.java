package facades;

import utils.EMF_Creator;
import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasProperty;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;

    private Movie m1 = new Movie(1981, "Raiders of the Lost Ark", new String[]{"Harrison Ford", "Karen Allen", "Paul Freeman"});
    private Movie m2 = new Movie(1984, "Indiana Jones and the Temple of Doom", new String[]{"Harrison Ford", "Kate Capshaw", "Jonathan Ke Quan"});
    private Movie m3 = new Movie(1989, "Indiana Jones and the Last Crusade", new String[]{"Harrison Ford", "Sean Connery", "Alison Doody"});

    public MovieFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/movies_travis_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = MovieFacade.getMovieFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = MovieFacade.getMovieFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testGetMovieCount() {
        assertEquals(3, facade.getMovieCount(), "Expects three rows in the database");
    }

    @Test
    public void testGetAllMovies() {
        List<Movie> movies = facade.getAllMovies();
        movies.forEach(m -> {
            System.out.println(m);
        });
        assertThat(movies, everyItem(hasProperty("name")));

    }

    @Test
    public void testGetMovieById() {
        Movie movie = facade.getMovieById(m1.getId());
        System.out.println(movie.getName());
        assertThat(movie.getActors()[1], containsString("Karen"));
    }
    
    @Test
    public void testMovieHasActors() {
        Movie movie = facade.getMovieById(m2.getId());
        assertThat(movie.getActors(), arrayContaining("Harrison Ford", "Kate Capshaw", "Jonathan Ke Quan"));
    }
    
    @Test
    public void testGetMoviesByName() {
        assertEquals(2, facade.getMoviesByName("jones").size(), "Expects two movies with name %jones%");
    }

}

package dto;

/**
 *
 * @author Casper Kruse Olesen
 */
public class MovieDTO {
    private Long id;
    private int year;
    private String name;
    private String[] actors;

    public MovieDTO(Long id, int year, String name, String[] actors) {
        this.id = id;
        this.year = year;
        this.name = name;
        this.actors = actors;
    }

    public Long getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public String[] getActors() {
        return actors;
    }
    
    
    
}

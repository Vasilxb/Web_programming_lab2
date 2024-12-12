package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class Album {
    private Long id;
    private String name;
    private String genre;
    private String releaseYear;
    private List<Song> songs;

    public Album(Long id, String name, String genre, String releaseYear) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }
}

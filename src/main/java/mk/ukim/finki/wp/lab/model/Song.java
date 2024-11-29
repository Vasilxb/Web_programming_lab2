package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Data
public class Song {
    public static Long idCounter = 0L;

    private Long id;
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    private Album album;
    private List<Artist> performers;


    public Song() {
        this.id = ++idCounter;
        this.performers = new ArrayList<>();
    }

    public Song(String trackId, String title, String genre, int releaseYear) {
        this.id = ++idCounter;
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>();
    }

    // Constructor with Album
    public Song(String trackId, String title, String genre, int releaseYear, Album album) {
        this.id = ++idCounter;
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.album = album;
        this.performers = new ArrayList<>();
    }

    public void addPerformer(Artist performer) {
        performers.add(performer);
    }
}

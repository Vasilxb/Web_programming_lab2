package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Data
public class Song {
    private Long id;
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    private Album album;
    private List<Artist> performers;


    public Song(String trackId, String title, String genre, int releaseYear) {
        this.id = new Random().nextLong(10000);
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>();
    }

    // Constructor with Album
    public Song(String trackId, String title, String genre, int releaseYear, Album album) {
        this.id = new Random().nextLong(10000);
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

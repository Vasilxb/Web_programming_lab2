package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;


@Getter
@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    @ManyToOne
    @JoinColumn(name = "album_id")
    Album album;
//    private List<Artist> performers;

    public Song(String trackId, String title, String genre, int releaseYear) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
//        this.performers = new ArrayList<>();
    }

    // Constructor with Album
    public Song(String trackId, String title, String genre, int releaseYear, Album album) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.album = album;
//        this.performers = new ArrayList<>();
    }

    public <E> List<E> getPerformers() {
        return List.of();
    }

//    public void addPerformer(Artist performer) {
//        performers.add(performer);
//    }
}

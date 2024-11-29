package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artistList = new ArrayList<>();
    public static List<Song> songList = new ArrayList<>();

    @PostConstruct
    public void init() {
        artistList.add(new Artist(11L, "Marina", "Diamandis", "Electra heart saviour"));
        artistList.add(new Artist(22L, "Avril", "Lavigne", "Pop punk princess"));
        artistList.add(new Artist(44L, "Kesha", "N/A", "Just looking for a joyride"));
        artistList.add(new Artist(612L, "Halsey", "N/A", "The Greatest Impersonator"));
        artistList.add(new Artist(327L, "Slayyyter", "YYY", "Miss YYY no comma"));

        songList.add(new Song("TR09", "Teen idle", "Alt/Indie", 2012));
        songList.add(new Song("TR05", "Hot", "Pop punk", 2007));
        songList.add(new Song("TR02", "JOYRIDE", "Pop", 2024));
        songList.add(new Song("TR01", "Only Living Girl in LA", "Sad pop", 2024));
        songList.add(new Song("TR04", "No Comma", "Pop/Hyper-pop", 2014));
    }
}
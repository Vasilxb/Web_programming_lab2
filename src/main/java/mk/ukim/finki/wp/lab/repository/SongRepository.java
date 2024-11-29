package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SongRepository {
    private final List<Song> songs = new ArrayList<>();

    public List<Song> findAll() {
        return songs;
    }

    public Optional<Song> findById(Long id) {
        return songs.stream().filter(song -> song.getId().equals(id)).findFirst();
    }

    public Optional<Song> findByTrackId(String trackId) {
        return songs.stream().filter(song -> song.getTrackId().equals(trackId)).findFirst();
    }

    public void save(Song song) {
        songs.removeIf(s -> s.getId().equals(song.getId()));
        songs.add(song);
    }

    public void deleteById(Long id) {
        songs.removeIf(song -> song.getId().equals(id));
    }
}

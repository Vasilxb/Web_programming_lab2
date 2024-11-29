package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {

    // Find all artists
    public List<Artist> findAll() {
        return DataHolder.artistList;
    }

    // Find artist by ID
    public Optional<Artist> findById(Long id) {
        return DataHolder.artistList.stream()
                .filter(artist -> artist.getId().equals(id))
                .findFirst();
    }

    // Save new artist or update existing one
    public void save(Artist artist) {
        DataHolder.artistList.add(artist);
    }

    // Delete an artist
    public void delete(Long id) {
        DataHolder.artistList.removeIf(artist -> artist.getId().equals(id));
    }
}

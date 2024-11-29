package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlbumRepository {
    public Optional<Album> findById(Long id) {
        return DataHolder.albumList.stream()
                .filter(album -> album.getId().equals(id))
                .findFirst();
    }
    public List<Album> findAll() {
        return DataHolder.albumList;
    }


    public void save(Album album) {
        if (album.getId() == null) {
            album.setId((long) (DataHolder.albumList.size() + 1));
            DataHolder.albumList.add(album);
        } else {
            DataHolder.albumList.replaceAll(existingAlbum -> existingAlbum.getId().equals(album.getId()) ? album : existingAlbum);
        }
    }


    public boolean deleteById(Long id) {
        return DataHolder.albumList.removeIf(album -> album.getId().equals(id));
    }
}

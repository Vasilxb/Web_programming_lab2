package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

import static mk.ukim.finki.wp.lab.bootstrap.DataHolder.songList;

@Repository
public class SongRepository {


    public List<Song> findAll() {
        return songList;
    }

    public Optional<Song> findById(Long id) {
        return songList.stream().filter(song -> song.getId().equals(id)).findFirst();
    }

    public Optional<Song> findByTrackId(String trackId) {
        return songList.stream().filter(song -> song.getTrackId().equals(trackId)).findFirst();
    }

    public Song save(Song song) {
        if(song.getAlbum() == null) {
            throw new IllegalArgumentException();
        }
        songList.removeIf(s -> s.getId().equals(song.getId()));
        songList.add(song);
        return song;
    }

    public void deleteById(Long id) {
        songList.removeIf(song -> song.getId().equals(id));
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        for(Song s: songList) {
            if(s.getTrackId().equals(song.getTrackId())){
                s.getPerformers().add(artist);
                return artist;
            }
        }
        return null;
    }
}

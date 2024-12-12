package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.Album;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();

    Artist addArtistToSong(Artist artist, Song song);

    Song findByTrackId(String trackId);

    Song findById(Long id);

    Song saveSong(String trackId, String title, String genre, int releaseYear, Album album);

    void deleteSong(Long id);
}
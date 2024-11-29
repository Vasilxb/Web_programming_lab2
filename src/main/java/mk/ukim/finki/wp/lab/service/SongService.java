package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.Album;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();

    Optional<Song> findById(Long songId);

    Song findByTrackId(String trackId);

    void saveSong(Song song);

    void deleteSongById(Long id);

    Album getAlbumById(Long albumId);

    Artist addArtistToSong(Artist artist, Song song);

    List<Song> findAll();
}
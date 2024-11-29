package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final AlbumService albumService;

    public SongServiceImpl(SongRepository songRepository, AlbumService albumService) {
        this.songRepository = songRepository;
        this.albumService = albumService;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public Optional<Song> findById(Long songId) {
        return songRepository.findById(songId);
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId)
                .orElseThrow(() -> new RuntimeException("Song not found"));
    }

    @Override
    public void saveSong(Song song) {
        songRepository.save(song);
    }

    @Override
    public void deleteSongById(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public Album getAlbumById(Long albumId) {
        return albumService.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Album not found"));
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        song.addPerformer(artist);
        saveSong(song);
        return artist;
    }


}

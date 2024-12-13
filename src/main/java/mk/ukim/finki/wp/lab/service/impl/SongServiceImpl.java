package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository ) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

//    @Override
//    public Artist addArtistToSong(Artist artist, Song song) {
//        return songRepository.addArtistToSong(artist, song);
//    }

    @Override
    public Song findByTrackId(String trackId) {
        Optional<Song> song = songRepository.findByTrackId(trackId);

        return song.orElse(null);
    }

    @Override
    public Song findById(Long id) {
        Optional<Song> song = songRepository.findById(id);

        return song.orElse(null);
    }

    @Override
    public Song saveSong(String trackId, String title, String genre, int releaseYear, Album album) {
        Song song=songRepository.findByTrackId(trackId).orElse(new Song());
        song.setTrackId(trackId);
        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);
        return songRepository.save(song);
    }

    @Override
    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

}

package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exception.InvalidAlbumIdException;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;

    public SongController(SongService songService, AlbumService albumService, ArtistService artistService) {
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", Optional.of(true));
            model.addAttribute("error", error);
        }

        model.addAttribute("songs", this.songService.listSongs());
        return "listSongs";
    }

    @GetMapping("/add-form")
    public String getAddSongPage(Model model) {
        List<Artist> artists = artistService.listArtists();
        List<Album> albums = albumService.findAll();
        model.addAttribute("artists", artists);
        model.addAttribute("albums", albums);
        return "addSong";
    }

    @PostMapping("/add")
    public String saveSong(@RequestParam(required = false) String title,
                           @RequestParam(required = false) String trackId,
                           @RequestParam(required = false) String genre,
                           @RequestParam(required = false) Integer releaseYear,
                           @RequestParam(required = false) Long albumId) {

        Album album = albumService.findById(albumId).orElseThrow(() -> new InvalidAlbumIdException(albumId));

        if (trackId == null) {
            return "redirect:/songs?error=Song with trackId not found!";
        }

        songService.saveSong(trackId, title, genre, releaseYear, album);
        return "redirect:/songs";
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        if(songService.findById(id) == null) {
            return "redirect:/songs?error=Song with trackId not found!";
        }
        songService.deleteSong(id);
        return "redirect:/songs";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditSongForm(@PathVariable Long id, Model model) {
        Song song = songService.findById(id);
        List<Artist> artists = artistService.listArtists();
        List<Album> albums = albumService.findAll();
        model.addAttribute("song", song);
        model.addAttribute("artists", artists);
        model.addAttribute("albums", albums);
        return "addSong";
    }

    @PostMapping("/edit/{songId}")
    public String editSong(@PathVariable Long songId,
                           @RequestParam(required = false) String title,
                           @RequestParam(required = false) String genre,
                           @RequestParam(required = false) Integer releaseYear,
                           @RequestParam(required = false) Long albumId) {
        Song song = songService.findById(songId);
        Album album = albumService.findById(albumId).orElseThrow(() -> new InvalidAlbumIdException(albumId));

        if (song == null) {
            return "redirect:/songs?error=Song with trackId not found!";
        }

        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);

        songService.saveSong(song.getTrackId(), song.getTitle(), song.getGenre(), song.getReleaseYear(), song.getAlbum());
        return "redirect:/songs";
    }
}

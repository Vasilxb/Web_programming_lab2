package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.SongService;
import mk.ukim.finki.wp.lab.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("songList", songService.listSongs());
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "listSongs";
    }

    @GetMapping("/edit-form/{songId}")
    public String getEditSongForm(@PathVariable Long songId, Model model) {
        Optional<Song> songOptional = songService.findById(songId);
        if (songOptional.isPresent()) {
            Song song = songOptional.get();
            List<Album> albums = albumService.findAll();
            model.addAttribute("song", song);
            model.addAttribute("albumList", albums);
            return "addSong";
        } else {
            return "redirect:/songs?error=Song+not+found";
        }
    }

    @GetMapping("/add-form")
    public String getAddSongPage(Model model) {
        List<Album> albumList = albumService.findAll();
        model.addAttribute("albumList", albumList);
        return "addSong";
    }

    @PostMapping("/edit/{songId}")
    public String saveEditedSong(@PathVariable Long songId,
                                 @RequestParam String title,
                                 @RequestParam String trackId,
                                 @RequestParam String genre,
                                 @RequestParam int releaseYear,
                                 @RequestParam Long albumId) {
        Optional<Song> songOptional = songService.findById(songId);
        if (songOptional.isPresent()) {
            Song song = songOptional.get();
            song.setTitle(title);
            song.setTrackId(trackId);
            song.setGenre(genre);
            song.setReleaseYear(releaseYear);

            Album album = albumService.findById(albumId)
                    .orElseThrow(() -> new RuntimeException("Album not found"));
            song.setAlbum(album);

            songService.saveSong(song);
            return "redirect:/songs";
        }
        return "redirect:/songs?error=Song+not+found";
    }

    @PostMapping("/add-form")
    public String addNewSong(@RequestParam String title,
                             @RequestParam String trackId,
                             @RequestParam String genre,
                             @RequestParam int releaseYear,
                             @RequestParam Long albumId) {
        Album album = albumService.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Album not found"));

        Song newSong = new Song(trackId, title, genre, releaseYear, album);
        songService.saveSong(newSong);

        return "redirect:/songs";
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        songService.deleteSongById(id);
        return "redirect:/songs";
    }
}

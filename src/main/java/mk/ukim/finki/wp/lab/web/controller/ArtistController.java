package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;
    private final SongService songService;

    @Autowired
    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping("/add/{songId}")
    public String getAddArtistPage(@PathVariable Long songId, Model model) {
        model.addAttribute("songId", songId);
        model.addAttribute("artists", artistService.findAll());
        return "addArtistToSong";
    }

    @PostMapping("/add/{songId}")
    public String addArtistToSong(@PathVariable Artist songId, @RequestParam Song artistId) {
        songService.addArtistToSong(songId, artistId);
        return "redirect:/songs";
    }
}

package com.microservice.music.controllers;

import com.microservice.music.dto.request.GetAlbumSongRequest;
import com.microservice.music.dto.request.GetMusicById;

import com.microservice.music.service.MusicService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/*
 * Created by dendy-prtha on 16/04/2019.
 * controller for music
 */

@RestController
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;

    @GetMapping(value = "/get-all-music",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllMusic() {
        return ResponseEntity.ok(musicService.getAllMusic().getData());
    }

    @GetMapping(value = "/get-all-album",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllMusicAlbum() {
        return ResponseEntity.ok(musicService.getAllAlbum().getData());
    }

    @PostMapping(value = "/get-album-music",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAlbumSong(@RequestBody GetAlbumSongRequest request) {
        return ResponseEntity.ok(musicService.getAlbumSong(request.getId()).getData());
    }

    @PostMapping(value = "/get-music-by-id",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSongById(@RequestBody GetMusicById request) {
        return ResponseEntity.ok(musicService.getSongById(request.getId()).getData());
    }

}

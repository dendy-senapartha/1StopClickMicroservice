package com.microservice.music.controllers;

import com.microservice.music.dto.AlbumDTO;
import com.microservice.music.dto.SongDTO;
import com.microservice.music.dto.request.GetAlbumSongRequest;
import com.microservice.music.dto.response.BaseResponse;
import com.microservice.music.model.Album;
import com.microservice.music.model.Product;
import com.microservice.music.repository.dao.AlbumDao;
import com.microservice.music.repository.dao.ProductDao;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/*
 * Created by dendy-prtha on 16/04/2019.
 * controller for product
 */

@RestController
@RequiredArgsConstructor
public class MusicController {

    private final ProductDao productRepository;

    private final AlbumDao albumRepository;

    private final ModelMapper modelMapper;

    @GetMapping(value = "/get-all-tracks",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllMusic() {
        List<SongDTO> songDTO = new ArrayList<>();
        List<Product> musicList = productRepository.findAll();
        for (Product product : musicList) {
            songDTO.add(modelMapper.map(product, SongDTO.class));
        }

        return ResponseEntity.ok(songDTO);
    }

    @GetMapping(value = "/get-all-album",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllMusicAlbum() {
        List<AlbumDTO> albumDTO = new ArrayList<>();
        List<Album> albumList = albumRepository.findAll();
        for (Album album : albumList) {
            albumDTO.add(modelMapper.map(album, AlbumDTO.class));
        }
        return ResponseEntity.ok(albumDTO);
    }

    @PostMapping(value = "/get-album-songs",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAlbumSong(@RequestBody GetAlbumSongRequest request) {
        List<SongDTO> songDTO = new ArrayList<>();
        List<Product> songList = new ArrayList<>();
        songList = productRepository.getAlbumProducts("" + request.getId());
        for (Product product : songList) {
            songDTO.add(modelMapper.map(product, SongDTO.class));
        }
        return ResponseEntity.ok(songDTO);
    }

}

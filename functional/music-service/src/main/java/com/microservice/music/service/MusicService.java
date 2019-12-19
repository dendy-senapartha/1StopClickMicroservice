package com.microservice.music.service;

import com.microservice.music.dto.AlbumDTO;
import com.microservice.music.dto.SongDTO;
import com.microservice.music.dto.response.DefaultResponse;
import com.microservice.music.model.Album;
import com.microservice.music.model.Product;
import com.microservice.music.repository.AlbumRepository;
import com.microservice.music.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * Created by dendy-prtha on 17/12/2019.
 * Music service
 */

@Service
@RequiredArgsConstructor
public class MusicService {

    private final ProductRepository productRepository;

    private final AlbumRepository albumRepository;

    private final ModelMapper modelMapper;

    public DefaultResponse getAllMusic() {
        List<SongDTO> songDTO = new ArrayList<>();
        List<Product> musicList = productRepository.findAll();
        for (Product product : musicList) {
            songDTO.add(modelMapper.map(product, SongDTO.class));
        }
        return new DefaultResponse(true, "", songDTO);
    }

    public DefaultResponse getAllAlbum() {
        List<AlbumDTO> albumDTO = new ArrayList<>();
        List<Album> albumList = albumRepository.findAll();
        for (Album album : albumList) {
            albumDTO.add(modelMapper.map(album, AlbumDTO.class));
        }
        return new DefaultResponse(true, "", albumDTO);
    }

    public DefaultResponse getAlbumSong(int albumId) {
        List<SongDTO> songDTO = new ArrayList<>();
        List<Product> songList = productRepository.getAlbumProducts(albumId);
        for (Product product : songList) {
            songDTO.add(modelMapper.map(product, SongDTO.class));
        }
        return new DefaultResponse(true, "", songDTO);
    }

    public DefaultResponse getSongById(int songId) {
        SongDTO songDTO = new SongDTO();
        Optional<Product> product = productRepository.findById(songId);
        if (product.isPresent()) {
            songDTO = modelMapper.map(product.get(), SongDTO.class);
        }
        return new DefaultResponse(true, "", songDTO);
    }
}

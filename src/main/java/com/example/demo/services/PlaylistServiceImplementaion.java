package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Playlist;
import com.example.demo.repositories.PlaylistRepository;

@Service
public class PlaylistServiceImplementaion implements PlaylistService {
	@Autowired
	PlaylistRepository repository;

	@Override
	public void addPlaylist(Playlist playlist) {
		repository.save(playlist);
		
	}

	@Override
	public List<Playlist> fetchAllPlaylist() {
		return repository.findAll();
	}
}
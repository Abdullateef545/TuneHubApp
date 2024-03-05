package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Song;
import com.example.demo.repositories.SongRepository;

@Service
public class SongServiceImplementation implements SongService{
	@Autowired
	SongRepository repository;
	
	public void addSongs(Song song) {
		repository.save(song);
	}

	
	public boolean songExists(String name) {
		Song song=repository.findByName(name);
		if(song==null) {
			return false;
		}
		else {
			return true;
		}
	}


	
	public List<Song> fetchAllSongs() {
		List<Song> songs=repository.findAll();
		return songs;
	}


	@Override
	public void updateSong(Song song) {
		repository.save(song);
		
	}
	
	

}

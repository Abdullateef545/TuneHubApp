package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Song;

public interface SongService {

	public void addSongs(Song song);
	
	public boolean songExists(String name);
	
	public List<Song> fetchAllSongs();

	public void updateSong(Song song);
}

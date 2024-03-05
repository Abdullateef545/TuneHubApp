package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.SongService;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class PlaylistController {
	
	@Autowired
	PlaylistService playlistservice;
	
	@Autowired
	SongService songservice;
	
	@GetMapping("/create-playlist")
	public String createPlaylist(Model model) {
		//fetching all the songs
		List<Song> songlist=songservice.fetchAllSongs();
		//adding songs to the model
		model.addAttribute("songlist", songlist);
		
		//sending to the create playlist html file
		return "createplaylist";
	}
	
	@PostMapping("/addplaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		//Adding the new playlist
		playlistservice.addPlaylist(playlist);
		
		//updating the song table
		List<Song> songlist=playlist.getSong();
		for(Song song: songlist) {
			song.getPlaylist().add(playlist);
			songservice.updateSong(song);
		}
		return "playlistsuccess";
	}
	
	@GetMapping("/viewPlaylist")
	public String viewPlaylist(Model model) {
		List<Playlist> plist=playlistservice.fetchAllPlaylist();
		
		model.addAttribute("plist", plist);
		return "viewplaylist";
	}
	
	
	
}
	

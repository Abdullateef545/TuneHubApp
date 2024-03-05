package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Song;
import com.example.demo.services.SongService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SongController {
	@Autowired
	SongService Songservice;
	
	@PostMapping("/addSongs")
	public String addSongs(@ModelAttribute Song song) {
		boolean songexists=Songservice.songExists(song.getName());
		if(songexists==false) {
			Songservice.addSongs(song);
			return "songsuccess";
		}
		else {
			return "songfail";
		}
	}
	
	@GetMapping("/view-songs")
	public String viewSongs(Model model) {
		List<Song> songlist=Songservice.fetchAllSongs();
		model.addAttribute("songlist", songlist);
		return "displaysongs";
	}
	
	@GetMapping("/viewSongs")
	public String viewCustomerSongs(Model model) {
		boolean primeCustomerStatus=false;
		if(primeCustomerStatus) {
			List<Song> songlist=Songservice.fetchAllSongs();
			model.addAttribute("songlist", songlist);
			return "displaysongs";
		}
		else {
			return "makePayment";
		}
	}
	
	
}

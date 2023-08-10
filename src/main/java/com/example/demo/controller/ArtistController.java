package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Email;
import com.example.demo.model.Artist;
import com.example.demo.service.EmailService;
import com.example.demo.service.ArtistService;

@Controller
public class ArtistController {
	
	@Autowired
	private ArtistService artistService;
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listArtists",artistService.getAllArtists());
		return "index";
	}
	
	@GetMapping("/showNewArtistForm")
	public String showNewArtist(Model model) {
		Artist artist = new Artist();
		model.addAttribute("artist", artist);
		return "new_artist";
	}
	
	@PostMapping("/saveArtist")
	public String saveArtist(@ModelAttribute("artist") Artist artist) {
		artistService.saveArtist(artist);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value="id") long id, Model model) {
		Artist artist = artistService.getArtistById(id);
		model.addAttribute("artist", artist);
		return "update_artist";
	}
	
	@GetMapping("/deleteArtist/{id}")
	public String deleteArtist(@PathVariable (value= "id") long id) {
		this.artistService.deleteArtistById(id);
		return "redirect:/";
	}
	@GetMapping("/showEmailForm/{id}")
	public String showEmailForm(@PathVariable (value="id") long id, Model model) {
		Artist artist = artistService.getArtistById(id);
		Email email = new Email();
		email.setFirstName(artist.getFirstName());
		email.setLastName(artist.getLastName());
		email.setTo(artist.getEmail());
		email.setFrom("noyeem.gm@gmail.com");
		model.addAttribute("email", email);
		return "email_form";
	}
	@PostMapping("/sendEmail")
	public String sendEmail(@ModelAttribute("email") Email email) {
		EmailService emailService = new EmailService(email);
		emailService.sendSimpleEmail();
		return "redirect:/";
	}
}

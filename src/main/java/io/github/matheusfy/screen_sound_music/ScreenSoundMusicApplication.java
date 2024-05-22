package io.github.matheusfy.screen_sound_music;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.matheusfy.screen_sound_music.repository.ArtistaRepository;
import io.github.matheusfy.screen_sound_music.repository.MusicaRepository;

@SpringBootApplication
public class ScreenSoundMusicApplication implements CommandLineRunner {

	@Autowired
	private ArtistaRepository artistaRepository;

	@Autowired
	private MusicaRepository musicaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ScreenSoundMusicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Aqui iremos fazer a chamada da aplicação
		MenuApplication menu = new MenuApplication(artistaRepository, musicaRepository);
		menu.showMenu();
	}

}

package io.github.matheusfy.screen_sound_music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.matheusfy.screen_sound_music.model.entiry.Musica;
import java.util.Set;

import io.github.matheusfy.screen_sound_music.model.entiry.Artista;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {

	Set<Musica> findByArtista(Artista artista);
}

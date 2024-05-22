package io.github.matheusfy.screen_sound_music.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.github.matheusfy.screen_sound_music.model.entiry.Artista;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {

	Optional<Artista> findByNome(String nomeArtista);

	@Query("SELECT a FROM Artista a WHERE a.nome LIKE %:nome%")
	List<Artista> findByNomeContainingIgnoreCase(String nome);
}

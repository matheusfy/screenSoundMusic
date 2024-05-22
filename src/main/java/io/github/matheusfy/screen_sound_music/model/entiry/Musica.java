package io.github.matheusfy.screen_sound_music.model.entiry;

import java.util.HashSet;
import java.util.Set;

import io.github.matheusfy.screen_sound_music.model.enums.TipoEstiloMusica;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "musicas")
public class Musica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Enumerated(EnumType.STRING)
	private TipoEstiloMusica estilo;

	@ManyToMany(mappedBy = "musicas", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Artista> artista = new HashSet<>();

	public Musica() {
	}

	public Musica(String nome, TipoEstiloMusica estilo, Artista artista) {
		this.nome = nome;
		this.estilo = estilo;
		this.artista.add(artista);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoEstiloMusica getEstilo() {
		return estilo;
	}

	public void setEstilo(TipoEstiloMusica estilo) {
		this.estilo = estilo;
	}

	public Set<Artista> getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista.add(artista);
	}

	@Override
	public String toString() {
		return """
				nome: %s
				estilo: %s
				artista: %s
				""".formatted(nome, estilo, artista.toString());
	}
}

package io.github.matheusfy.screen_sound_music.model.entiry;

import java.util.HashSet;
import java.util.Set;

import io.github.matheusfy.screen_sound_music.model.enums.TipoArtista;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "artistas")
public class Artista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String nome;

	@Enumerated(EnumType.STRING)
	private TipoArtista categoria;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "artistas_musicas", joinColumns = @JoinColumn(name = "artista_id"), inverseJoinColumns = @JoinColumn(name = "musica_id"))
	private Set<Musica> musicas = new HashSet<>();

	public Artista() {
	}

	public Artista(String nome, TipoArtista categoria) {
		this.nome = nome;
		this.categoria = categoria;
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

	public TipoArtista getEstilo() {
		return categoria;
	}

	public void setEstilo(TipoArtista categoria) {
		this.categoria = categoria;
	}

	public Set<Musica> getMusicas() {
		return musicas;
	}

	public void addMusica(Musica musica) {
		this.musicas.add(musica);
		musica.getArtista().add(this);
	}

	@Override
	public String toString() {
		return "Nome=" + nome + ", estilo=" + categoria;
	}

}

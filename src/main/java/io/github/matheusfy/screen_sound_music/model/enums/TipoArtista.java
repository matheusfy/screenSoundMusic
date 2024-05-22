package io.github.matheusfy.screen_sound_music.model.enums;

/**
 * The TipoArtista enum represents the type of artist.
 * It can be one of the following: SOLO, DUPLA, BANDA.
 */
public enum TipoArtista {
	SOLO("solo"), DUPLA("dupla"), BANDA("banda");

	private String tipo;

	TipoArtista(String tipo) {
		this.tipo = tipo;
	}

	public static TipoArtista fromString(String tipo) {
		for (TipoArtista tipoArtista : TipoArtista.values()) {
			if (tipoArtista.tipo.equalsIgnoreCase(tipo)) {
				return tipoArtista;
			}
		}
		throw new IllegalArgumentException("Tipo de artista não encontrado");
	}
}

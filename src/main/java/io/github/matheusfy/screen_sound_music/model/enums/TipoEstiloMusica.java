package io.github.matheusfy.screen_sound_music.model.enums;

public enum TipoEstiloMusica {
	ROCK("Rock"),
	POP("Pop"),
	MPB("Mpb"),
	SERTANEJO("Sertanejo"),
	FUNK("Funk"),
	RAP("Rap"),
	ELETRONICA("Eletronica"),
	SAMBA("Samba"),
	PAGODE("Pagode"),
	KPOP("K-Pop"),
	OUTRO("Outro");

	private final String estilo;

	TipoEstiloMusica(String estilo) {
		this.estilo = estilo;
	}

	public static TipoEstiloMusica fromString(String estilo) {
		for (TipoEstiloMusica tipoEstilo : TipoEstiloMusica.values()) {
			if (tipoEstilo.estilo.equalsIgnoreCase(estilo)) {
				return tipoEstilo;
			}
		}
		throw new IllegalArgumentException("Estilo não encontrado");
	}
}

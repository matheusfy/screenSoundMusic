package io.github.matheusfy.screen_sound_music;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import io.github.matheusfy.screen_sound_music.api.OpenAiApi;
import io.github.matheusfy.screen_sound_music.model.entiry.Artista;
import io.github.matheusfy.screen_sound_music.model.entiry.Musica;
import io.github.matheusfy.screen_sound_music.model.enums.TipoArtista;
import io.github.matheusfy.screen_sound_music.model.enums.TipoEstiloMusica;
import io.github.matheusfy.screen_sound_music.repository.ArtistaRepository;
import io.github.matheusfy.screen_sound_music.repository.MusicaRepository;

public class MenuApplication {

	private Scanner cmd;
	private ArtistaRepository artistaRepository;
	private MusicaRepository musicaRepository;
	private OpenAiApi openaiApi;

	public MenuApplication(ArtistaRepository artistaRepository, MusicaRepository musicaRepository) {
		this.artistaRepository = artistaRepository;
		this.musicaRepository = musicaRepository;
		this.openaiApi = new OpenAiApi();
		this.cmd = new Scanner(System.in);
	}

	public void showMenu() {

		System.out.println("Bem vindo ao Screen Sound Music");
		String text = """
				1 - Cadastrar artistas.
				2 - Cadastrar músicas.
				3 - Listar musicas.
				4 - Buscar músicas por artista.
				5 - Pesquisar dados sobre um artista.
				6 - Listar artistas cadastrados.
				0 - Sair.
				""";
		String opcao = "-1";
		while (!opcao.equals("0")) {
			System.out.println(text);
			opcao = cmd.nextLine();

			switch (opcao) {
				case "1" -> {
					cadastrarArtista();
				}
				case "2" -> {
					try {
						cadastraMusica();
					} catch (Exception error) {
						System.out.println(error.getMessage());
					}
				}
				case "3" -> {
					buscarTodasMusicas();
				}
				case "4" -> {

					buscarMusicasPorArtista();
				}
				case "5" -> {
					System.out.println("Digite o nome do artista/banda que gostaria de pesquisar: ");
					String nome = cmd.nextLine();

					System.out.println(openaiApi.getArtistaInfo(nome));

				}
				case "6" -> {
					buscarArtistasCadastrados();
				}

				case "0" -> {
					System.out.println("Saindo...");
					break;
				}
				default -> {
					System.out.println("Opção inválida");
				}
			}
		}
	}

	private void buscarTodasMusicas() {
		System.out.println("Listar musicas");
		List<Musica> musicas = musicaRepository.findAll();
		if (!musicas.isEmpty()) {
			musicas.forEach(m -> System.out.println(m));
		} else {
			System.out.println("Nenhuma música cadastrada");
		}
	}

	private void buscarArtistasCadastrados() {
		System.out.println("Listar artistas cadastrados");

		List<Artista> artistas = artistaRepository.findAll();
		if (!artistas.isEmpty()) {
			artistas.forEach(a -> System.out.println(a));
		} else {
			System.out.println("Nenhum artista cadastrado");
		}
	}

	private void buscarMusicasPorArtista() {
		System.out.println("Digite o nome do artista: ");
		String nomeArtista = cmd.nextLine();

		Optional<Artista> artista = artistaRepository.findByNome(nomeArtista);

		if (artista.isPresent()) {
			Artista artistaObj = artista.get();
			Set<Musica> musicas = musicaRepository.findByArtista(artistaObj);
			if (!musicas.isEmpty()) {
				System.out
						.println("********************* Músicas encontradas " + artistaObj.getNome() + " *********************");
				musicas.forEach(m -> System.out.println(m));
				System.out.println("Total de músicas encontradas: " + musicas.size());
			} else {
				System.out.println("Nenhuma música encontrada para o artista " + nomeArtista);
			}

		} else {
			System.out.println("Artista não encontrado");
		}
	}

	private Artista cadastrarArtista() {
		System.out.println("Cadastrar artistas");
		System.out.println("Digite o nome do artista: ");
		String nome = cmd.nextLine();

		System.out.println("Digite a categoria do artista: ");
		String categoria = cmd.nextLine();
		TipoArtista tipoArtista = TipoArtista.fromString(categoria);

		System.out.println("Nome: " + nome + " Categoria: " + tipoArtista);
		Artista artista = new Artista(nome, tipoArtista);

		try {
			return artistaRepository.save(artista);
		} catch (Exception e) {
			System.out.println("Erro ao salvar artista: " + e.getMessage());
		}
		return null;
	}

	private void cadastraMusica() {
		System.out.println("Cadastrar músicas");

		System.out.println("Digite o nome da música: ");
		String nome = cmd.nextLine();

		System.out.println("Digite o estilo da música: ");
		String estilo = cmd.nextLine();

		TipoEstiloMusica tipoEstilo = TipoEstiloMusica.fromString(estilo);

		System.out.println("Digite 1 para inserir nome do artista ou 2 para buscar: ");

		Optional<Artista> artista = Optional.empty();
		switch (cmd.nextLine()) {
			case "1" -> {
				System.out.println("Digite o nome do artista: ");
				String nomeArtista = cmd.nextLine();

				artista = artistaRepository.findByNome(nomeArtista);
				if (artista.isEmpty()) {
					System.out.println("Artista não encontrado. Deseja cadastrar? (S/N)");
					confirmarCadastro();
				}
			}

			case "2" -> {
				System.out.println("Digite uma parte do nome do artista: ");
				String parteNome = cmd.nextLine();

				List<Artista> artistas = artistaRepository.findByNomeContainingIgnoreCase(parteNome);
				if (artistas.isEmpty()) {
					System.out.println("Não encontramos nenhum artista com esse nome. Deseja cadastrar um novo artista? (S/N)");
					confirmarCadastro();
				} else {
					System.out.println("Artistas encontrados: ");
					Integer index = 0;
					artistas.forEach(a -> System.out.println((index + 1) + " - " + a.getNome()));

					System.out.println("Digite o número do artista: ");
					artista = Optional.ofNullable(artistas.get(Integer.parseInt(cmd.nextLine()) - 1));
				}
			}
			default -> {
				throw new IllegalArgumentException("Opção inválida");
			}
		}

		if (artista.isPresent()) {
			Artista artistaObj = artista.get();
			System.out.println("Nome: " + nome + " Estilo: " + tipoEstilo + " Artista: " + artistaObj.getNome());

			Musica musica = new Musica(nome, tipoEstilo, artistaObj);
			artistaObj.addMusica(musica);
			artistaRepository.save(artistaObj);
			musicaRepository.save(musica);

			System.out.println("Musica salva com sucesso");

		} else {
			System.out.println("Artista não encontrado. Portanto musica nao sera salva");
		}
	}

	private Artista confirmarCadastro() {
		if (cmd.nextLine().equalsIgnoreCase("S")) {
			return cadastrarArtista();
		} else {
			throw new IllegalArgumentException("Saindo pois não é possível cadastrar uma musica sem um artista.");
		}
	}
}

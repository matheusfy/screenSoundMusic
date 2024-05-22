package io.github.matheusfy.screen_sound_music.api;

import java.util.List;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class OpenAiApi {

	private static final String API_KEY = System.getenv("OPENAI_API_KEY");
	private OpenAiService apiService = new OpenAiService(API_KEY);

	public OpenAiApi() {

	}

	public String getArtistaInfo(String artistaNome) {
		String context = "Fale um pouco sobre que é o artista ou banda: " + artistaNome;

		;
		// aqui fazer o build da requisição

		CompletionRequest request = CompletionRequest.builder()
				.model("gpt-3.5-turbo-instruct")
				.prompt(context)
				.maxTokens(1000)
				.temperature(0.4).build();

		try {
			List<CompletionChoice> choices = apiService.createCompletion(request).getChoices();

			System.out.println("Respostas devolvidas pela api da OpenAI");
			choices.forEach(choice -> {
				System.out.println(choice.getText());
			});

			// devolvo a primeira choice para o usuário
			return choices.get(0).getText().trim();
		} catch (Exception error) {
			System.out.println("Erro ao buscar informações sobre o artista");
			return "";
		}

	}
}

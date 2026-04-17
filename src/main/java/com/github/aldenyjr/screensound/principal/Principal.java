package com.github.aldenyjr.screensound.principal;

import com.github.aldenyjr.screensound.models.Artista;
import com.github.aldenyjr.screensound.models.Musica;
import com.github.aldenyjr.screensound.models.TipoArtista;
import com.github.aldenyjr.screensound.repository.ArtistaRepository;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ArtistaRepository artistaRepository;

    public Principal(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    ************************
                    * SCREEN SOUND MÚSICAS *
                    ************************
                    
                    Menu de Opções:
                    1 - Cadastrar artistas
                    2 - Cadastrar músicas
                    
                    0 - Sair
                    """;
            System.out.printf(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 0:
                    System.out.printf("Saindo...");
                    break;
                default:
                    System.out.printf("Opção Invalida, verifique e tente novamente.");
                    break;
            }
        }
    }

    private void cadastrarArtistas() {
        var cadastrarNovo = "s";
        while (cadastrarNovo.equalsIgnoreCase("s")) {
            switch (cadastrarNovo) {
                case "s":
                    System.out.printf("informe o nome desse artista: ");
                    var nomeArtista = scanner.nextLine();
                    System.out.printf("informe o tipo desse artista: (solo, dupla, banda): ");
                    var tipoArtista = scanner.nextLine();
                    TipoArtista tipoArtistaEnum = TipoArtista.fromString(tipoArtista);
                    Artista artista = new Artista(nomeArtista, tipoArtistaEnum);
                    artistaRepository.save(artista);

                    System.out.printf("Cadastrar outro artista? (S/N): ");
                    cadastrarNovo = scanner.nextLine();
                    break;
                case "n":
                    break;
                default:
                    System.out.println("Opção invalida, tente novamente!");
                    break;
            }
        }
    }

    private void cadastrarMusicas() {
        System.out.printf("Cadastrar musica de que artista?: ");
        var nome = scanner.nextLine();

        Optional<Artista> artista = artistaRepository.findByNomeIgnoreCase(nome);

        if(artista.isPresent()) {
            System.out.printf("Informe o titulo da música: ");
            var nomeMusica = scanner.nextLine();
            Musica musica = new Musica(nomeMusica);
            artista.get().adicionaMusica(musica);
            artistaRepository.save(artista.get());
        } else {
            System.out.println("Artista não encontrado");
        }
    }
}

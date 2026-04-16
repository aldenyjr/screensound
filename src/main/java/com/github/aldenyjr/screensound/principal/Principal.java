package com.github.aldenyjr.screensound.principal;

import com.github.aldenyjr.screensound.models.Artista;
import com.github.aldenyjr.screensound.models.TipoArtista;
import com.github.aldenyjr.screensound.repository.ArtistaRepository;

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
                    
                    0 - Sair
                    """;
            System.out.printf(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
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
        while (cadastrarNovo.equals("s")) {
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
                    return;
                default:
                    System.out.println("Opção invalida, tente novamente!");
                    break;
            }

        }

    }
}

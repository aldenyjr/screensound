package com.github.aldenyjr.screensound.principal;

import com.github.aldenyjr.screensound.models.Artista;
import com.github.aldenyjr.screensound.models.Musica;
import com.github.aldenyjr.screensound.models.TipoArtista;
import com.github.aldenyjr.screensound.repository.ArtistaRepository;

import java.util.Comparator;
import java.util.List;
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
                    ************************************
                    *       SCREEN SOUND MÚSICAS       *
                    ************************************
                    
                    Menu de Opções:
                    1 - Cadastrar artistas
                    2 - Cadastrar músicas
                    3 - Listar artistas
                    4 - Listar músicas
                    5 - Buscar músicas por artista
                    6 - Deletar artista
                    
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
                case 3:
                    listarArtistas();
                    break;
                case 4:
                    listarMusicas();
                    break;
                case 5:
                    buscarMusicasPorArtista();
                    break;
                case 6:
                    deletarArtista();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção Invalida, verifique e tente novamente.");
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

        if (artista.isPresent()) {
            System.out.printf("Informe o titulo da música: ");
            var nomeMusica = scanner.nextLine();
            Musica musica = new Musica(nomeMusica);
            artista.get().adicionaMusica(musica);
            artistaRepository.save(artista.get());
        } else {
            System.out.println("Artista não encontrado");
        }
    }

    private void listarArtistas() {
        List<Artista> artistas = artistaRepository.findAll();
        artistas.forEach(System.out::println);
    }

    private void listarMusicas() {
        List<Artista> artistas = artistaRepository.findAll();
        artistas.stream()
                .flatMap(artista -> artista.getMusicas()
                        .stream()
                        .sorted(Comparator.comparing(Musica::getTitulo)))
                .forEach(System.out::println);
    }

    private void buscarMusicasPorArtista() {
        System.out.printf("Informe o nome do artista que deseja buscar: ");
        var nomeArtista = scanner.nextLine();
        Optional<Artista> artista = artistaRepository.findByNomeIgnoreCase(nomeArtista);
        if (artista.isPresent()) {
            if (artista.get().getMusicas().isEmpty()) {
                System.out.printf("O artista: %s ainda não possui músicas cadastradas\n", artista.get().getNome());
                return;
            }
            artista.get().getMusicas().stream()
                    .sorted(Comparator.comparing(Musica::getTitulo))
                    .forEach(System.out::println);
        } else {
            System.out.println("Artista não encontrado, verifique e tente novamente");
        }
    }

    private void deletarArtista() {
        System.out.printf("Informe o nome do artista que deseja deletar: ");
        var nomeArtista = scanner.nextLine();
        Optional<Artista> artista = artistaRepository.findByNomeIgnoreCase(nomeArtista);
        if (artista.isPresent()) {
            artistaRepository.delete(artista.get());
            System.out.println("Artista deletado com sucesso!");
        } else {
            System.out.println("Artista não encontrado, verifique e tente novamente");
        }
    }
}

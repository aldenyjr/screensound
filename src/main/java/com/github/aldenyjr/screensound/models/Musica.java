package com.github.aldenyjr.screensound.models;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = true)
    private String titulo;

    @ManyToOne
    private Artista artista;

    public Musica() {
    }

    public Musica(String nomeMusica) {
        this.titulo = nomeMusica;
    }

    public Musica(String titulo, Artista artista) {
        this.titulo = titulo;
        this.artista = artista;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return String.format(
                "Musica[titulo='%s', artista='%s']",
                titulo,
                artista != null ? artista.getNome() : "N/A"
        );
    }
}

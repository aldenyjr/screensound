package com.github.aldenyjr.screensound.models;

public enum TipoArtista {
    SOLO,
    DUPLA,
    BANDA;

    public static TipoArtista fromString(String valor) {
        for (TipoArtista tipo : TipoArtista.values()) {
            if (tipo.name().equalsIgnoreCase(valor)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo inválido: " + valor);
    }
}

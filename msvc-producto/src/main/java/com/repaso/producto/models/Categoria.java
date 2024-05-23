package com.repaso.producto.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;


public enum Categoria {
    ASEO_PERSONAL("aseo personal"),
    ALIMENTOS("ali"),
    MUEBLES("muebles"),
    ASEO_HOGAR("aseo hogar");

    private String valor;

    Categoria(String valor){
        this.valor=valor;
    }


    @JsonValue
    public String getValue() {
        return this.name().toLowerCase(); // Convertir el nombre del enum a minúsculas
    }

    @JsonCreator
    public static Categoria fromString(String texto) {
        if (texto != null) {
            for (Categoria tipo : Categoria.values()) {
                if (tipo.name().equalsIgnoreCase(texto.trim())) {
                    return tipo;
                }
            }
        }
        return null;
    }




}

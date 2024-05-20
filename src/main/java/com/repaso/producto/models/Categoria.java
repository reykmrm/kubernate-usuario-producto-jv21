package com.repaso.producto.models;

public enum Categoria {
    ASEO_PERSONAL("aseo personal"),
    ALIMENTOS("alimentos"),
    MUEBLES("muebles"),
    ASEO_HOGAR("aseo hogar");

    private String valor;

    Categoria(String valor){
        this.valor=valor;
    }



}

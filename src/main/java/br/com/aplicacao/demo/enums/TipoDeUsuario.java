package br.com.aplicacao.demo.enums;

public enum TipoDeUsuario {

    ADMIN("admin"),
    USER("user");

    private String tipo;
    TipoDeUsuario(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }
}

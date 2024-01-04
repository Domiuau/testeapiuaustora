package br.com.aplicacao.demo.enums;

public enum EstadoDoProduto {

    NOVO("novo"),
        SEMI_NOVO("semi-novo"),
            USADO("usado");

    private String estado;

    EstadoDoProduto(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

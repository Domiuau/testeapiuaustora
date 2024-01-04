package br.com.aplicacao.demo.enums;

public enum TipoDeGarantia {

    VENDEDOR("vendedor"),
    UAUSTORA("uaustora"),
    FABRICANTE("fabricante");

    private String garantia;

    TipoDeGarantia(String garantia) {
        this.garantia = garantia;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }
}

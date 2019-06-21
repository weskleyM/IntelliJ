package com.web.main.entity;

public class Item {

    private Prato prato;
    private int qtd;

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        this.prato = prato;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Item(Prato prato, int qtd) {
        super();
        this.prato = prato;
        this.qtd = qtd;
    }

    public Item() {
        super();
    }
}

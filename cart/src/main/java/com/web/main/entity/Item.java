package com.web.main.entity;

public class Item {

    private Prato prato;
    private int qtd;

    public Item() {
    }
    
    public Item(Prato prato, int qtd) {
        this.prato = prato;
        this.qtd = qtd;
    }    

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
    
}

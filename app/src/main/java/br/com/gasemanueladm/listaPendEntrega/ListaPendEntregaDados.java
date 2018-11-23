package br.com.gasemanueladm.listaPendEntrega;

import java.io.Serializable;

public class ListaPendEntregaDados implements Serializable {
    //Variáveis
    private int     pedNum;
    private String  pedIdCliente;
    private String  pedNomeCliente;
    private String  pedData;
    private String  pedHora;
    private String  prodNome;
    private String  prodModelo;
    private String  prodGasQuant;
    private String  prodGasValorUnit;
    private String  pedTaxaEntrega;
    private String  pedValorTotal;
    private String  pedObserv;
    private String  pedStatus;


    public int getPedNum() {
        return pedNum;
    }

    public void setPedNum(int pedNum) {
        this.pedNum = pedNum;
    }

    public String getPedIdCliente() {
        return pedIdCliente;
    }

    public void setPedIdCliente(String pedIdCliente) {
        this.pedIdCliente = pedIdCliente;
    }

    public String getPedNomeCliente() {
        return pedNomeCliente;
    }

    public void setPedNomeCliente(String pedNomeCliente) {
        this.pedNomeCliente = pedNomeCliente;
    }

    public String getPedData() {
        return pedData;
    }

    public void setPedData(String pedData) {
        this.pedData = pedData;
    }

    public String getPedHora() {
        return pedHora;
    }

    public void setPedHora(String pedHora) {
        this.pedHora = pedHora;
    }

    public String getProdNome() {
        return prodNome;
    }

    public void setProdNome(String prodNome) {
        this.prodNome = prodNome;
    }

    public String getProdModelo() {
        return prodModelo;
    }

    public void setProdModelo(String prodModelo) {
        this.prodModelo = prodModelo;
    }

    public String getProdGasQuant() {
        return prodGasQuant;
    }

    public void setProdGasQuant(String prodGasQuant) {
        this.prodGasQuant = prodGasQuant;
    }

    public String getProdGasValorUnit() {
        return prodGasValorUnit;
    }

    public void setProdGasValorUnit(String prodGasValorUnit) {
        this.prodGasValorUnit = prodGasValorUnit;
    }

    public String getPedTaxaEntrega() {
        return pedTaxaEntrega;
    }

    public void setPedTaxaEntrega(String pedTaxaEntrega) {
        this.pedTaxaEntrega = pedTaxaEntrega;
    }

    public String getPedValorTotal() {
        return pedValorTotal;
    }

    public void setPedValorTotal(String pedValorTotal) {
        this.pedValorTotal = pedValorTotal;
    }

    public String getPedObserv() {
        return pedObserv;
    }

    public void setPedObserv(String pedObserv) {
        this.pedObserv = pedObserv;
    }

    public String getPedStatus() {
        return pedStatus;
    }

    public void setPedStatus(String pedStatus) {
        this.pedStatus = pedStatus;
    }
}

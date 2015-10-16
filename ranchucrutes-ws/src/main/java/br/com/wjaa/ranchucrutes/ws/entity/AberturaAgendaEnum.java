package br.com.wjaa.ranchucrutes.ws.entity;

/**
 * Created by wagner on 15/10/15.
 */
public enum AberturaAgendaEnum {

    SEMANAL(7),
    DUAS_SEMANAS(14),
    TRES_SEMANAS(21),
    QUATRO_SEMANAS(28),
    MENSAL(30),
    BISTRAL(60);

    private int dias;
    private AberturaAgendaEnum(int dias){
        this.dias = dias;
    }

    public int getDias() {
        return dias;
    }
}

package br.com.wjaa.ranchucrutes.commons.helper;

/**
 * Created by wagner on 01/09/15.
 */
public enum DiaSemana {

    SEGUNDA(1),
    TERCA(2),
    QUARTA(4),
    QUINTA(8),
    SEXTA(16),
    SABADO(32),
    DOMINGO(64);

    private DiaSemana(Integer id){
        this.id = id;
    }
    private Integer id;

    public Integer getId() {
        return id;
    }

    public static boolean temSegunda(Integer value){
        return temDiaSemana(SEGUNDA, value);
    }

    public static boolean temTerca(Integer value){
        return temDiaSemana(TERCA, value);
    }

    public static boolean temQuarta(Integer value){
        return temDiaSemana(QUARTA, value);
    }

    public static boolean temQuinta(Integer value){
        return temDiaSemana(QUINTA, value);
    }

    public static boolean temSexta(Integer value){
        return temDiaSemana(SEXTA, value);
    }

    public static boolean temSabado(Integer value){
        return temDiaSemana(SABADO, value);
    }

    public static boolean temDomingo(Integer value){
        return temDiaSemana(DOMINGO, value);
    }

    public static boolean temDiaSemana(DiaSemana diaSemana, Integer value){
        if (value == null){
            return false;
        }

        return (diaSemana.getId() & value) > 0;
    }
}

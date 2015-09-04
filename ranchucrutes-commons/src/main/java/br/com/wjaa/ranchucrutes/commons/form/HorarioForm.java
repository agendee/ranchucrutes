package br.com.wjaa.ranchucrutes.commons.form;

import br.com.wjaa.ranchucrutes.commons.helper.DiaSemana;
import br.com.wjaa.ranchucrutes.commons.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by wagner on 22/08/15.
 */
public class HorarioForm {

    private Long id;
    private String horaIni;
    private String horaFim;
    private Integer[] diasSemana;
    private Integer diaSemana;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(String horaIni) {
        this.horaIni = horaIni;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public Integer[] getDiasSemana() {
        return diasSemana;
    }


    public void setDiasSemana(Integer[] diasSemana) {
        this.diasSemana = diasSemana;
    }


    public Integer getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(Integer diaSemana) {
        this.diaSemana = diaSemana;
    }

    @JsonIgnore
    public boolean temSegunda(){
        return DiaSemana.temSegunda(diaSemana);
    }

    @JsonIgnore
    public boolean temTerca(){
        return DiaSemana.temTerca(diaSemana);
    }

    @JsonIgnore
    public boolean temQuarta(){
        return DiaSemana.temQuarta(diaSemana);
    }

    @JsonIgnore
    public boolean temQuinta(){
        return DiaSemana.temQuinta(diaSemana);
    }

    @JsonIgnore
    public boolean temSexta(){
        return DiaSemana.temSexta(diaSemana);
    }

    @JsonIgnore
    public boolean temSabado(){
        return DiaSemana.temSabado(diaSemana);
    }

    @JsonIgnore
    public boolean temDomingo(){
        return DiaSemana.temDomingo(diaSemana);
    }



    @JsonIgnore
    public Integer getTotDiaSemana(){
        Integer total = 0;
        if (diasSemana != null){
            for (Integer dia : diasSemana){
                total += dia;
            }
        }
        return total;
    }

    @JsonIgnore
    public String getLabel(){
        String label = "De ";

        if (temSegunda()){
            label += "Seg, ";
        }
        if (temTerca()){
            label += "Ter, ";
        }
        if (temQuarta()){
            label += "Qua, ";
        }
        if (temQuinta()){
            label += "Qui, ";
        }
        if (temSexta()){
            label += "Sex, ";
        }
        if (temSabado()){
            label += "Sáb, ";
        }
        if (temDomingo()){
            label += "Dom, ";
        }

        if (horaIni != null && horaFim != null){
            label += "das " + horaIni + " até " + horaFim;
        }

        if (diaSemana == null || diaSemana < 1){
            return "Escolha um horário para abertura da sua agenda.";
        }

        return label;

    }

}

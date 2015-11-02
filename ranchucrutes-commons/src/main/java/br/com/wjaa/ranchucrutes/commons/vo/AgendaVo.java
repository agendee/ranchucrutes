package br.com.wjaa.ranchucrutes.commons.vo;

import br.com.wjaa.ranchucrutes.commons.helper.JacksonArrayDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by wagner on 09/10/15.
 */
public class AgendaVo {
    private ProfissionalBasicoVo profissional;

    @JsonSerialize(using = JacksonArrayDateSerializer.class)
    private Date[] horariosDisponiveis;

    public ProfissionalBasicoVo getProfissional() {
        return profissional;
    }

    public void setProfissional(ProfissionalBasicoVo profissional) {
        this.profissional = profissional;
    }

    public Date[] getHorariosDisponiveis() {
        return horariosDisponiveis;
    }

    public void setHorariosDisponiveis(Date[] horariosDisponiveis) {
        this.horariosDisponiveis = horariosDisponiveis;
    }
}

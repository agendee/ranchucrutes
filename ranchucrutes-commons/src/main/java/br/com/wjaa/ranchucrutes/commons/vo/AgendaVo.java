package br.com.wjaa.ranchucrutes.commons.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wagner on 09/10/15.
 */
public class AgendaVo {
    private ProfissionalBasicoVo profissional;
    private List<Date> horariosDisponiveis;

    public ProfissionalBasicoVo getProfissional() {
        return profissional;
    }

    public void setProfissional(ProfissionalBasicoVo profissional) {
        this.profissional = profissional;
    }

    public List<Date> getHorariosDisponiveis() {
        return horariosDisponiveis;
    }

    public void setHorariosDisponiveis(List<Date> horariosDisponiveis) {
        this.horariosDisponiveis = horariosDisponiveis;
    }

    public void putHorariosDisponiveis(List<Date> horariosDisponiveis) {
        if (this.horariosDisponiveis == null){
            this.horariosDisponiveis = new ArrayList<>();
        }
        this.horariosDisponiveis.addAll(horariosDisponiveis);
    }
}

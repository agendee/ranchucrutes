package br.com.wjaa.ranchucrutes.commons.vo;

/**
 * Created by wagner on 18/08/15.
 */
public class ResultadoLoginVo {

    private MedicoBasicoVo medico;
    private String msg;

    public MedicoBasicoVo getMedico() {
        return medico;
    }

    public void setMedico(MedicoBasicoVo medico) {
        this.medico = medico;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

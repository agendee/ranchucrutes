package br.com.wjaa.ranchucrutes.commons.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by wagner on 18/08/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultadoLoginVo {

    public enum StatusLogin{
        SUCESSO("Logado"),
        ERRO("Usúario ou senha inválido!"),
        ERRO_SOCIAL("Usuário não cadastrado!"),
        ACESSO_NAO_CONFIRMADO("Seu acesso não foi confirmado!");

        private String msg;
        private StatusLogin(String msg){
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }


    private MedicoBasicoVo medico;
    private PacienteVo paciente;
    private StatusLogin status;

    public MedicoBasicoVo getMedico() {
        return medico;
    }

    public void setMedico(MedicoBasicoVo medico) {
        this.medico = medico;
    }

    public StatusLogin getStatus() {
        return status;
    }

    public void setStatus(StatusLogin status) {
        this.status = status;
    }

    @JsonIgnore
    public Boolean isSucesso(){
        return StatusLogin.SUCESSO.equals(this.status);
    }

    @JsonIgnore
    public Boolean isErro(){
        return StatusLogin.ERRO.equals(this.status);
    }

    @JsonIgnore
    public Boolean isAcessoNaoConfirmado(){
        return StatusLogin.ACESSO_NAO_CONFIRMADO.equals(this.status);
    }

    public PacienteVo getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteVo paciente) {
        this.paciente = paciente;
    }
}

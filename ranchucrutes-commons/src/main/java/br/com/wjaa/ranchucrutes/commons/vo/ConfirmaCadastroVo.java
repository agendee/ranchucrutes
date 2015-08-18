package br.com.wjaa.ranchucrutes.commons.vo;

/**
 * Created by wagner on 18/08/15.
 */
public class ConfirmaCadastroVo {

    public enum StatusConfirmacaoCadastro{
        SUCESSO("Confirmação realizada com sucesso!"),
        CODIGO_INVALIDO("Código informado é invalido!"),
        CADASTRO_JA_CONFIRMADO("Seu cadastro já foi confirmado!"),
        UNKOWN_ERROR("Ocorreu um erro ao confirmar o seu cadastro.");

        private String msg;

        private StatusConfirmacaoCadastro(String msg){
            this.msg = msg;
        }

        public String getMsg(){
            return this.msg;
        }

    }

    public ConfirmaCadastroVo(){}
    public ConfirmaCadastroVo(StatusConfirmacaoCadastro status){
        this.status = status;
    }


    private StatusConfirmacaoCadastro status;


    public StatusConfirmacaoCadastro getStatus() {
        return status;
    }

    public void setStatus(StatusConfirmacaoCadastro status) {
        this.status = status;
    }

    public Boolean isError(){
        return (StatusConfirmacaoCadastro.CODIGO_INVALIDO.equals(status) ||
            StatusConfirmacaoCadastro.UNKOWN_ERROR.equals(status) ||
            StatusConfirmacaoCadastro.CADASTRO_JA_CONFIRMADO.equals(status));
    }

}

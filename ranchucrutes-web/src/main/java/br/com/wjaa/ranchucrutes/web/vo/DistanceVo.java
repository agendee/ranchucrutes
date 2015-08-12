package br.com.wjaa.ranchucrutes.web.vo;

/**
 * Created by wagner on 15/06/15.
 */
public class DistanceVo {

    private String distancia;
    private String duracao;

    public DistanceVo(){}

    public DistanceVo(String distance, String duracao){
        this.distancia = distance;
        this.duracao = duracao;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "DistanceVo{" +
                "distancia='" + distancia + '\'' +
                ", duracao='" + duracao + '\'' +
                '}';
    }
}

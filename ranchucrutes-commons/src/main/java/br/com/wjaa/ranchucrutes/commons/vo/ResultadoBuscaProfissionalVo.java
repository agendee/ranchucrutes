package br.com.wjaa.ranchucrutes.commons.vo;

import java.util.List;

/**
 * Created by wagner on 22/07/15.
 */
public class ResultadoBuscaProfissionalVo {

    private List<ProfissionalBasicoVo> profissionais;
    private Double latitude;
    private Double longitude;
    private ProfissionalBasicoVo profissionalMaisProximo;
    private Double distanceInKm;



    public List<ProfissionalBasicoVo> getProfissionais() {
        return profissionais;
    }

    public void setProfissionais(List<ProfissionalBasicoVo> profissionais) {
        this.profissionais = profissionais;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public ProfissionalBasicoVo getProfissionalMaisProximo() {
        return profissionalMaisProximo;
    }

    public void setProfissionalMaisProximo(ProfissionalBasicoVo profissionalMaisProximo) {
        this.profissionalMaisProximo = profissionalMaisProximo;
    }

    public Double getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(Double distanceInKm) {
        this.distanceInKm = distanceInKm;
    }
}

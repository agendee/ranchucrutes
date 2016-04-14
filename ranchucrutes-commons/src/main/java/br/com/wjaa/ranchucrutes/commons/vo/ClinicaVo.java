package br.com.wjaa.ranchucrutes.commons.vo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by wagner on 15/10/15.
 */
public class ClinicaVo {

    private static final Log LOG = LogFactory.getLog(ResultadoBuscaClinicaVo.class);

    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private Double latitude;
    private Double longitude;
    private List<ProfissionalBasicoVo> profissionais;
    private MapTipoLocalidade mapTipoLocalidade = MapTipoLocalidade.PARTICULAR;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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


    public List<ProfissionalBasicoVo> getProfissionais() {
        return profissionais;
    }

    public void setProfissionais(List<ProfissionalBasicoVo> profissionais) {
        this.profissionais = profissionais;
    }

    public MapTipoLocalidade getMapTipoLocalidade() {
        return mapTipoLocalidade;
    }

    public void setMapTipoLocalidade(MapTipoLocalidade mapTipoLocalidade) {
        this.mapTipoLocalidade = mapTipoLocalidade;
    }

    public boolean isMesmoEndereco(ClinicaVo clinica) {
        if (clinica == null){
            return false;
        }
        if (CollectionUtils.isEmpty(clinica.getProfissionais())){
            return false;
        }
        ProfissionalBasicoVo p = clinica.getProfissionais().get(0);

        return p.getLatitude().equals(this.latitude) &&
                p.getLongitude().equals(this.longitude);
    }

    public boolean isMesmaClinica(ClinicaVo clinica) {
        if (clinica == null){
            return false;
        }
        return clinica.getId().equals(this.id);
    }

    public void appendClinica(ClinicaVo clinica) {

        if (clinica == null){
            return;
        }
        if (CollectionUtils.isEmpty(clinica.getProfissionais())){
            return;
        }

        if (this.isMesmaClinica(clinica) && mapTipoLocalidade != MapTipoLocalidade.EDIFICIO){
            mapTipoLocalidade = MapTipoLocalidade.CLINICA;
        }else if (this.isMesmoEndereco(clinica)){
            mapTipoLocalidade = MapTipoLocalidade.EDIFICIO;
        }else{
            LOG.error("Aqui é um erro:" + clinica + " " + this);
        }
        profissionais.add(clinica.getProfissionais().get(0));


    }
}

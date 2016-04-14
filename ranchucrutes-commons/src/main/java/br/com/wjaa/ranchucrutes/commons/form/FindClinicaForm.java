package br.com.wjaa.ranchucrutes.commons.form;
import br.com.wjaa.ranchucrutes.commons.vo.LocationVo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by wagner on 02/07/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FindClinicaForm {

    private Integer idEspecialidade;
    private Integer idCategoria;
    private Integer idPlanoSaude;
    private String cep;
    private LocationVo location;

    public Integer getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(Integer idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getIdPlanoSaude() {
        return idPlanoSaude;
    }

    public void setIdPlanoSaude(Integer idPlanoSaude) {
        this.idPlanoSaude = idPlanoSaude;
    }

    public LocationVo getLocation() {
        return location;
    }

    public void setLocation(LocationVo location) {
        this.location = location;
    }
}

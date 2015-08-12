package br.com.wjaa.ranchucrutes.ws.vo;

import br.com.wjaa.ranchucrutes.ws.entity.ClinicaEntity;
import br.com.wjaa.ranchucrutes.ws.entity.EnderecoEntity;
import br.com.wjaa.ranchucrutes.ws.entity.MedicoEntity;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wagner on 22/07/15.
 */
public class MedicoBasicoVo {

    private String nome;
    private Integer crm;
    private String espec;
    private Double latitude;
    private Double longitude;
    private String endereco;
    private String telefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCrm() {
        return crm;
    }

    public void setCrm(Integer crm) {
        this.crm = crm;
    }

    public String getEspec() {
        return espec;
    }

    public void setEspec(String espec) {
        this.espec = espec;
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

    public static List<MedicoBasicoVo> toListMedicoBasico(List<MedicoEntity> medicosProximos) {
        List<MedicoBasicoVo> medicosVos = new ArrayList<>(medicosProximos.size());
        for(MedicoEntity me : medicosProximos){
            medicosVos.add(MedicoBasicoVo.toMedicoBasico(me));
        }
        return medicosVos;
    }

    public static MedicoBasicoVo toMedicoBasico(MedicoEntity me){
        MedicoBasicoVo mv = new MedicoBasicoVo();
        mv.setNome(me.getNome());
        mv.setCrm(me.getCrm());
        if (!CollectionUtils.isEmpty(me.getEspecialidades())){
            mv.setEspec(me.getEspecialidades().get(0).getNome());
        }
        if (!CollectionUtils.isEmpty(me.getClinicas())){
            ClinicaEntity c = me.getClinicas().get(0).getClinica();
            if (c != null){
                EnderecoEntity e = c.getEndereco();
                mv.setLatitude(e.getLatitude());
                mv.setLongitude(e.getLongitude());
                mv.setEndereco(e.getLogradouro() + ", " + e.getNumero() + " - " + e.getBairro());
                if ( c.getTelefone() != null ){

                    mv.setTelefone(c.getDdd() + " " + c.getTelefone());
                }
            }
        }
        return mv;
    }
}

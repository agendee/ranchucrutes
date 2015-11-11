package br.com.wjaa.ranchucrutes.ws.adapter;

import br.com.wjaa.ranchucrutes.commons.vo.PacienteVo;
import br.com.wjaa.ranchucrutes.ws.entity.RedeSocialEnum;
import br.com.wjaa.ranchucrutes.ws.entity.PacienteEntity;
import org.springframework.beans.BeanUtils;

/**
 * Created by wagner on 11/09/15.
 */
public class PacienteAdapter {


    public static PacienteVo toPacienteVo(PacienteEntity pacienteEntity) {

        PacienteVo pacienteVo = new PacienteVo();

        if (pacienteEntity != null){
            BeanUtils.copyProperties(pacienteEntity,pacienteVo,"senha");
            if (pacienteEntity.getRedeSocial() != null){
                pacienteVo.setAuthType(pacienteEntity.getRedeSocial().getSocialType());
            }
            pacienteVo.setId(pacienteEntity.getIdLogin());
            if (pacienteEntity.getConvenioCategoria() != null){
                pacienteVo.setConvenioCategoria(RanchucrutesAdapter.toConvenioCategoriaVo(pacienteEntity.getConvenioCategoria()));
            }
        }
        return pacienteVo;
    }

    public static PacienteEntity fromPacienteVo(PacienteVo pacienteVo) {
        PacienteEntity entity = new PacienteEntity();

        if (pacienteVo != null){
            BeanUtils.copyProperties(pacienteVo,entity);
            entity.setIdLogin(pacienteVo.getId());
            if (pacienteVo.getAuthType() != null){
                entity.setRedeSocial(RedeSocialEnum.adapterSocialType(pacienteVo.getAuthType()));
            }

        }
        return entity;
    }
}

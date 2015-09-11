package br.com.wjaa.ranchucrutes.ws.adapter;

import br.com.wjaa.ranchucrutes.commons.vo.MedicoBasicoVo;
import br.com.wjaa.ranchucrutes.commons.vo.PacienteVo;
import br.com.wjaa.ranchucrutes.ws.entity.PacienteEntity;
import br.com.wjaa.ranchucrutes.ws.entity.RedeSocialEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

/**
 * Created by wagner on 11/09/15.
 */
public class PacienteAdapter {


    public static PacienteVo toPacienteVo(PacienteEntity pacienteEntity) {

        PacienteVo pacienteVo = new PacienteVo();

        if (pacienteEntity != null){
            BeanUtils.copyProperties(pacienteEntity,pacienteVo,"senha");
            pacienteVo.setAuthType(pacienteEntity.getRedeSocial().getSocialType());
            pacienteVo.setId(pacienteEntity.getIdLogin());
        }
        return pacienteVo;
    }

    public static PacienteEntity fromPacienteVo(PacienteVo pacienteVo) {
        PacienteEntity entity = new PacienteEntity();

        if (pacienteVo != null){
            BeanUtils.copyProperties(pacienteVo,entity);
            entity.setRedeSocial(RedeSocialEnum.adapterSocialType(pacienteVo.getAuthType()));

        }
        return entity;
    }
}

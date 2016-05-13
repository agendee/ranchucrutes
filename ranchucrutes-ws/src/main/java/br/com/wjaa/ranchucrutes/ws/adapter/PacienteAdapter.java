package br.com.wjaa.ranchucrutes.ws.adapter;

import br.com.wjaa.ranchucrutes.commons.vo.ConvenioCategoriaVo;
import br.com.wjaa.ranchucrutes.commons.vo.PacienteVo;
import br.com.wjaa.ranchucrutes.ws.entity.ConvenioCategoriaEntity;
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
            if (pacienteEntity.getConvenios() != null){
                pacienteVo.setConveniosCategorias(RanchucrutesAdapter.toConveniosCategoriasVo(pacienteEntity.getConvenios()));
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
            if (pacienteVo.getConveniosCategorias() != null){
                for (ConvenioCategoriaVo ccVo : pacienteVo.getConveniosCategorias()){
                    ConvenioCategoriaEntity c = new ConvenioCategoriaEntity();
                    c.setId(ccVo.getId());
                    entity.addConvenioCategoria(c);
                }
            }

        }
        return entity;
    }
}

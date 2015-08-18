package br.com.wjaa.ranchucrutes.ws.adapter;

import br.com.wjaa.ranchucrutes.commons.form.MedicoForm;
import br.com.wjaa.ranchucrutes.commons.vo.MedicoBasicoVo;
import br.com.wjaa.ranchucrutes.ws.entity.ClinicaEntity;
import br.com.wjaa.ranchucrutes.ws.entity.EnderecoEntity;
import br.com.wjaa.ranchucrutes.ws.entity.EspecialidadeEntity;
import br.com.wjaa.ranchucrutes.ws.entity.MedicoEntity;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wagner on 12/08/15.
 */
public class MedicoAdapter {

    public static MedicoEntity fromMedicoForm(MedicoForm form){
        MedicoEntity entity = new MedicoEntity();
        BeanUtils.copyProperties(form,entity);
        for (Integer idEspec : form.getIdEspecialidade() ){
            entity.addIdEspecialidade(idEspec);
        }
        return entity;
    }

    public static MedicoForm toMedicoForm(MedicoEntity entity){
        MedicoForm form = new MedicoForm();
        BeanUtils.copyProperties(entity,form);
        Integer [] idEspecs = new Integer[]{};
        if (entity.getEspecialidades() != null){
            for (EspecialidadeEntity espec : entity.getEspecialidades() ){
                ArrayUtils.add(idEspecs,espec.getId());
            }
        }
        return form;
    }

    public static List<MedicoBasicoVo> toListMedicoBasico(List<MedicoEntity> medicosProximos) {
        List<MedicoBasicoVo> medicosVos = new ArrayList<>(medicosProximos.size());
        for(MedicoEntity me : medicosProximos){
            medicosVos.add(toMedicoBasico(me));
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

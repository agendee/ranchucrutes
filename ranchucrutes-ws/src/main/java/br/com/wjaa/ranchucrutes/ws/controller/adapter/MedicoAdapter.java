package br.com.wjaa.ranchucrutes.ws.controller.adapter;

import br.com.wjaa.ranchucrutes.commons.form.MedicoForm;
import br.com.wjaa.ranchucrutes.ws.entity.EspecialidadeEntity;
import br.com.wjaa.ranchucrutes.ws.entity.MedicoEntity;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanUtils;

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

}

package br.com.wjaa.ranchucrutes.ws.adapter;

import br.com.wjaa.ranchucrutes.commons.form.ClinicaForm;
import br.com.wjaa.ranchucrutes.commons.form.MedicoForm;
import br.com.wjaa.ranchucrutes.commons.form.MedicoFullForm;
import br.com.wjaa.ranchucrutes.commons.vo.MedicoBasicoVo;
import br.com.wjaa.ranchucrutes.ws.entity.*;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
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
                idEspecs = (Integer[]) ArrayUtils.add(idEspecs,espec.getId());
            }
        }
        form.setIdEspecialidade(idEspecs);
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
        mv.setId(me.getIdLogin());
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

    public static MedicoFullForm toMedicoFullForm(MedicoEntity entity) {
        MedicoFullForm medicoFull = new MedicoFullForm();
        MedicoForm medicoForm = toMedicoForm(entity);
        medicoFull.setMedico(medicoForm);
        List<ClinicaForm> clinicasForm = toClinicaForm(entity.getClinicas());
        medicoFull.setClinicas(clinicasForm);
        return medicoFull;
    }

    public static MedicoEntity fromMedicoFullForm(MedicoFullForm form) {
        MedicoEntity entity = fromMedicoForm(form.getMedico());
        entity.setClinicas(fromClinicaForm(form.getClinicas(),form.getMedico().getIdLogin()));
        return entity;
    }

    public static List<ClinicaForm> toClinicaForm(List<MedicoClinicaEntity> clinicas) {

        List<ClinicaForm> clinicasForm = new ArrayList<>();

        if (clinicas != null){

            for (MedicoClinicaEntity medicoClinica : clinicas){
                ClinicaEntity clinica = medicoClinica.getClinica();
                ClinicaForm form = new ClinicaForm();

                BeanUtils.copyProperties(medicoClinica,form,"ddd","telefone");

                if (clinica != null){
                    BeanUtils.copyProperties(clinica,form,"id");
                    form.setIdClinica(clinica.getId());
                    form.setIdPlanos(getIdConvenios(clinica.getConvenios()));
                }

                if (clinica.getAgenda() != null){
                    AgendaEntity agenda = clinica.getAgenda();
                    BeanUtils.copyProperties(agenda,form,"id","idClinica");
                    form.setIdAgenda(agenda.getId());
                }
                clinicasForm.add(form);
            }

        }

        return clinicasForm;

    }


    public static List<MedicoClinicaEntity> fromClinicaForm(List<ClinicaForm> clinicasForm, Long idMedico) {

        List<MedicoClinicaEntity> medicoClinicas = new ArrayList<>();

        if (clinicasForm != null){

            for (ClinicaForm form : clinicasForm){
                MedicoClinicaEntity medicoClinica = new MedicoClinicaEntity();
                //dados do medico na clinica
                BeanUtils.copyProperties(form,medicoClinica,"ddd","telefone");
                ClinicaEntity clinicaEntity = new ClinicaEntity();

                //dados da clinica
                BeanUtils.copyProperties(form,clinicaEntity,"id");
                clinicaEntity.setId(form.getIdClinica());

                //dados da agenda
                AgendaEntity agenda = new AgendaEntity();
                BeanUtils.copyProperties(form,agenda,"id");
                agenda.setId(form.getIdAgenda());

                clinicaEntity.setAgenda(agenda);
                clinicaEntity.setConvenios(getConvenios(form.getIdPlanos()));

                medicoClinica.setClinica(clinicaEntity);

                medicoClinicas.add(medicoClinica);
            }

        }

        return medicoClinicas;

    }

    private static List<ConvenioCategoriaEntity> getConvenios(Integer[] idPlanos) {
        List<ConvenioCategoriaEntity> convenios = new ArrayList<>();
        if (idPlanos != null){
            for (Integer id: idPlanos){
                ConvenioCategoriaEntity cc = new ConvenioCategoriaEntity();
                cc.setId(id);
                convenios.add(cc);
            }
        }
        return convenios;
    }


    private static Integer[] getIdConvenios(List<ConvenioCategoriaEntity> planos) {
        if (planos != null){
            Integer [] idPlanos = new Integer[planos.size()];
            for (int i = 0; i < planos.size(); i ++){
                idPlanos[i] = planos.get(i).getId();
            }
            return idPlanos;
        }
        return null;
    }
}

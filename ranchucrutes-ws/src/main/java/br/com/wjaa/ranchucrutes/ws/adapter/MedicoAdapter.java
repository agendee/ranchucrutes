package br.com.wjaa.ranchucrutes.ws.adapter;

import br.com.wjaa.ranchucrutes.commons.form.*;
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
        if (form != null){
            BeanUtils.copyProperties(form,entity);
            if (form.getIdEspecialidade() != null){
                for (Integer idEspec : form.getIdEspecialidade() ){
                    entity.addIdEspecialidade(idEspec);
                }
            }
        }
        return entity;
    }

    public static MedicoForm toMedicoForm(MedicoEntity entity){
        MedicoForm form = new MedicoForm();

        if (entity != null){
            BeanUtils.copyProperties(entity,form);
            Integer [] idEspecs = new Integer[]{};
            if (entity.getEspecialidades() != null){
                for (EspecialidadeEntity espec : entity.getEspecialidades() ){
                    idEspecs = (Integer[]) ArrayUtils.add(idEspecs,espec.getId());
                }
            }
            form.setIdEspecialidade(idEspecs);
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
        entity.setClinicas(fromClinicaForm(form.getClinicas()));
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
                    form.setCategorias(toCategoriaForm(clinica.getConvenios()));

                    if (clinica.getAgenda() != null){
                        AgendaEntity agenda = clinica.getAgenda();
                        BeanUtils.copyProperties(agenda,form,"id","idClinica","agendaHorarios");
                        form.setIdAgenda(agenda.getId());

                        if (agenda.getAgendaHorarios()!= null){
                            List<HorarioForm> listHorarios = new ArrayList<>(agenda.getAgendaHorarios().size());
                            for(AgendaHorarioEntity h : agenda.getAgendaHorarios()){
                                HorarioForm hf = new HorarioForm();
                                BeanUtils.copyProperties(h,hf);
                                listHorarios.add(hf);
                            }
                            form.setAgendaHorarios(listHorarios);
                        }

                    }
                    if (clinica.getEndereco() != null){
                        EnderecoEntity endereco = clinica.getEndereco();
                        EnderecoForm endForm = new EnderecoForm();
                        BeanUtils.copyProperties(endereco, endForm);
                        form.setEndereco(endForm);
                    }
                }

                clinicasForm.add(form);
            }

        }

        return clinicasForm;

    }


    public static List<MedicoClinicaEntity> fromClinicaForm(List<ClinicaForm> clinicasForm) {

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

                if (form.getEndereco() != null){
                    EnderecoEntity enderecoEntity = new EnderecoEntity();
                    BeanUtils.copyProperties(form.getEndereco(),enderecoEntity);
                    clinicaEntity.setEndereco(enderecoEntity);
                }

                //dados da agenda
                AgendaEntity agenda = new AgendaEntity();
                BeanUtils.copyProperties(form,agenda,"id");
                agenda.setId(form.getIdAgenda());

                if (form.getAgendaHorarios()!= null){
                    List<AgendaHorarioEntity> listHorarios = new ArrayList<>(form.getAgendaHorarios().size());
                    for(HorarioForm hf : form.getAgendaHorarios()){
                        AgendaHorarioEntity h = new AgendaHorarioEntity();
                        BeanUtils.copyProperties(hf,h);
                        h.setDiaSemana(hf.getTotDiaSemana());
                        listHorarios.add(h);
                    }
                    agenda.setAgendaHorarios(listHorarios);
                }

                clinicaEntity.setAgenda(agenda);
                clinicaEntity.setConvenios(toCategoriaEntity(form.getIdsCategoria()));
                medicoClinica.setClinica(clinicaEntity);
                medicoClinicas.add(medicoClinica);
            }

        }

        return medicoClinicas;

    }

    private static List<ConvenioCategoriaEntity> toCategoriaEntity(Integer [] idsCategoria) {
        List<ConvenioCategoriaEntity> convenios = new ArrayList<>();
        if (idsCategoria != null){
            for (Integer id: idsCategoria){
                ConvenioCategoriaEntity cc = new ConvenioCategoriaEntity();
                cc.setId(id);
                convenios.add(cc);
            }
        }
        return convenios;
    }


    private static List<ConvenioCategoriaForm> toCategoriaForm(List<ConvenioCategoriaEntity> conveniosEntity) {
        List<ConvenioCategoriaForm> convenios = new ArrayList<>();
        if (conveniosEntity != null){
            for (ConvenioCategoriaEntity e : conveniosEntity){
                convenios.add(new ConvenioCategoriaForm(e.getId(),e.getConvenio().getNome() + " - " + e.getNome()));
            }
            return convenios;
        }
        return null;
    }
}

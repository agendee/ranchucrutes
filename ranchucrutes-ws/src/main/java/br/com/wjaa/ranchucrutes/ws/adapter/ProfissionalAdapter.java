package br.com.wjaa.ranchucrutes.ws.adapter;

import br.com.wjaa.ranchucrutes.commons.form.*;
import br.com.wjaa.ranchucrutes.commons.vo.ClinicaVo;
import br.com.wjaa.ranchucrutes.commons.vo.ProfissionalBasicoVo;
import br.com.wjaa.ranchucrutes.ws.entity.*;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wagner on 12/08/15.
 */
public class ProfissionalAdapter {

    public static ProfissionalEntity fromProfissionalForm(ProfissionalForm form){
        ProfissionalEntity entity = new ProfissionalEntity();
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

    public static ProfissionalForm toProfissionalForm(ProfissionalEntity entity){
        ProfissionalForm form = new ProfissionalForm();

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

    public static List<ProfissionalBasicoVo> toListProfissionalBasico(List<ProfissionalEntity> profissionalsProximos) {
        List<ProfissionalBasicoVo> profissionalsVos = new ArrayList<>(profissionalsProximos.size());
        for(ProfissionalEntity me : profissionalsProximos){
            profissionalsVos.add(toProfissionalBasico(me,null));
        }
        return profissionalsVos;
    }

    public static ProfissionalBasicoVo toProfissionalBasico(ProfissionalEntity me, Integer idParceiro){
        ProfissionalBasicoVo profissionalBasicoVo = new ProfissionalBasicoVo();
        profissionalBasicoVo.setId(me.getIdLogin());
        profissionalBasicoVo.setNome(me.getNome());
        profissionalBasicoVo.setNumeroRegistro(me.getNumeroRegistro());

        if (!CollectionUtils.isEmpty(me.getEspecialidades())){
            EspecialidadeEntity e = me.getEspecialidades().get(0);
            profissionalBasicoVo.setEspec(e.getNome());
            profissionalBasicoVo.setIdProfissao(e.getProfissao().getId());
            profissionalBasicoVo.setNomeProfissao(e.getProfissao().getNome());
        }
        profissionalBasicoVo.setIdParceiro(idParceiro);

        if (!CollectionUtils.isEmpty(me.getClinicas())){
            ClinicaEntity c = me.getClinicas().get(0).getClinica();

            if (c != null){

                /*************************/
                /*TODO ESSE PEDAÇO AQUI ESTÁ OBSOLETO RETIRAR, MAS OLHAR OS PONTOS QUE USAM
                * O IDEAL É ESTAR O TELEFONE E ENDERECO DENTRO DE CLINICA QUE JÁ ESTA NO LOOP ABAIXO*/
                EnderecoEntity e = c.getEndereco();
                if (e != null){
                    profissionalBasicoVo.setLatitude(e.getLatitude());
                    profissionalBasicoVo.setLongitude(e.getLongitude());
                    profissionalBasicoVo.setEndereco(e.getLogradouro() + ", " + e.getNumero() + " - " + e.getBairro());
                }
                if ( c.getTelefone() != null ){
                    profissionalBasicoVo.setTelefone(c.getDdd() + " " + c.getTelefone());
                }
                /*****************************************/
            }


            for (ProfissionalClinicaEntity clinicaEntity : me.getClinicas()){
                profissionalBasicoVo.addClinica(toClinicaVo(clinicaEntity.getClinica()));
                profissionalBasicoVo.setTemAgenda(clinicaEntity.getClinica().getAgenda() != null);
            }

        }

        return profissionalBasicoVo;
    }

    public static ClinicaVo toClinicaVo(ClinicaEntity c) {
        ClinicaVo clienteVo = new ClinicaVo();
        if (c != null){
            clienteVo.setId(c.getId());
            clienteVo.setNome(c.getNome());
            EnderecoEntity e = c.getEndereco();
            if (e != null){
                clienteVo.setLatitude(e.getLatitude());
                clienteVo.setLongitude(e.getLongitude());
                clienteVo.setEndereco(e.getLogradouro() + ", " + e.getNumero() + " - " + e.getBairro());
            }
            if ( c.getTelefone() != null ){
                clienteVo.setTelefone(c.getDdd() + " " + c.getTelefone());
            }
        }
        return clienteVo;
    }

    public static ProfissionalFullForm toProfissionalFullForm(ProfissionalEntity entity) {
        ProfissionalFullForm profissionalFull = new ProfissionalFullForm();
        ProfissionalForm profissionalForm = toProfissionalForm(entity);
        profissionalFull.setProfissional(profissionalForm);
        List<ClinicaForm> clinicasForm = toClinicaForm(entity.getClinicas());
        profissionalFull.setClinicas(clinicasForm);
        return profissionalFull;
    }

    public static ProfissionalEntity fromProfissionalFullForm(ProfissionalFullForm form) {
        ProfissionalEntity entity = fromProfissionalForm(form.getProfissional());
        entity.setClinicas(fromClinicaForm(form.getClinicas()));
        return entity;
    }

    public static List<ClinicaForm> toClinicaForm(List<ProfissionalClinicaEntity> clinicas) {

        List<ClinicaForm> clinicasForm = new ArrayList<>();

        if (clinicas != null){

            for (ProfissionalClinicaEntity profissionalClinica : clinicas){
                ClinicaEntity clinica = profissionalClinica.getClinica();
                ClinicaForm form = new ClinicaForm();

                BeanUtils.copyProperties(profissionalClinica,form,"ddd","telefone");

                if (clinica != null){
                    BeanUtils.copyProperties(clinica,form,"id");
                    form.setIdClinica(clinica.getId());
                    form.setCategorias(toCategoriaForm(clinica.getConvenios()));


                    if (clinica.getAgenda() != null){
                        AgendaEntity agenda = clinica.getAgenda();
                        BeanUtils.copyProperties(agenda,form,"id","idClinica","agendaHorarios");
                        form.setIdAgenda(agenda.getId());
                        if ( agenda.getAberturaAgenda() != null ){
                            form.setAberturaAgenda(agenda.getAberturaAgenda().toString());
                        }
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


    public static List<ProfissionalClinicaEntity> fromClinicaForm(List<ClinicaForm> clinicasForm) {

        List<ProfissionalClinicaEntity> profissionalClinicas = new ArrayList<>();

        if (clinicasForm != null){

            for (ClinicaForm form : clinicasForm){
                ProfissionalClinicaEntity profissionalClinica = new ProfissionalClinicaEntity();
                //dados do profissional na clinica
                BeanUtils.copyProperties(form,profissionalClinica,"ddd","telefone");
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
                if (StringUtils.isNotBlank(form.getAberturaAgenda())){
                    agenda.setAberturaAgenda(AberturaAgendaEnum.valueOf(form.getAberturaAgenda()));
                }

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
                profissionalClinica.setClinica(clinicaEntity);
                profissionalClinicas.add(profissionalClinica);
            }

        }

        return profissionalClinicas;

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

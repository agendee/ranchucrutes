package br.com.wjaa.ranchucrutes.ws.dao;

import br.com.wjaa.ranchucrutes.commons.vo.ClinicaVo;
import br.com.wjaa.ranchucrutes.commons.vo.LocationVo;
import br.com.wjaa.ranchucrutes.commons.vo.ProfissionalBasicoVo;
import br.com.wjaa.ranchucrutes.framework.dao.GenericDaoImpl;
import br.com.wjaa.ranchucrutes.ws.entity.ClinicaEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wagner on 4/1/16.
 */
@Repository
public class ClinicaDaoImpl extends GenericDaoImpl<ClinicaEntity, Long> implements ClinicaDao  {


    private static final Log LOG = LogFactory.getLog(ClinicaDaoImpl.class);

    @Resource(name = "findClinicaByConvenioQuery")
    private String findClinicaByConvenioQuery;

    @Resource(name = "findClinicaQuery")
    private String findClinicaQuery;

    @Override
    public List<ClinicaVo> findClinicaByConvenio(Integer idEspecialidade, Integer idConvenio, LocationVo location, double raioPrecisao) {
        LOG.debug("findClinicaByConvenio, idEspecialidade=" + idEspecialidade + ", idConvenio=" + idConvenio +
                ", location=" + location + ", raioPrecisao = " + raioPrecisao);

        String [] nameParams = new String[]{"maxLatitude","minLatitude","maxLongitude","minLongitude",
                "idEspecialidade","idConvenio"};

        Object [] values = new Object[]{
                location.getMaxLatitude(raioPrecisao),
                location.getMinLatitude(raioPrecisao),
                location.getMaxLongitude(raioPrecisao),
                location.getMinLongitude(raioPrecisao),
                idEspecialidade,
                idConvenio};

        List<ClinicaVo> resultList = this.find(findClinicaByConvenioQuery, nameParams, values);

        LOG.debug("findClinicaByConvenio, resultList=" + resultList.size());

        return resultList;

    }

    @Override
    public List<ClinicaVo> findClinicaParticular(Integer idEspecialidade, LocationVo location, double raioPrecisao) {

        LOG.debug("findClinicaByConvenio, idEspecialidade=" + idEspecialidade +
                ", location=" + location + ", raioPrecisao = " + raioPrecisao);

        String [] nameParams = new String[]{"maxLatitude","minLatitude","maxLongitude","minLongitude",
                "idEspecialidade","aceitaParticular"};
         Object[] values = new Object[]{
                        location.getMaxLatitude(raioPrecisao),
                        location.getMinLatitude(raioPrecisao),
                        location.getMaxLongitude(raioPrecisao),
                        location.getMinLongitude(raioPrecisao),
                        idEspecialidade,
                        true};

        List<ClinicaVo> resultList = this.find(findClinicaQuery,nameParams,values);

        LOG.debug("findClinicaByConvenio, resultList=" + resultList.size());

        return resultList;
    }


    private List<ClinicaVo> find(String query, String[] nameParams, Object[] values){
        Query q = this.getSession().createSQLQuery(query);
        for(int i = 0; i < nameParams.length; i ++ ){
            q.setParameter(nameParams[i],values[i]);
        }

        q.setResultTransformer(new ClinicaEntityTransformer());
        return q.list();
    }


    class ClinicaEntityTransformer implements ResultTransformer{

        @Override
        public Object transformTuple(Object[] tuple, String[] aliases) {

            ClinicaVo clinicaVo = new ClinicaVo();

            ProfissionalBasicoVo p = new ProfissionalBasicoVo();
            clinicaVo.setProfissionais(new ArrayList<ProfissionalBasicoVo>());
            clinicaVo.getProfissionais().add(p);
            for(int i = 0; i < tuple.length; i++){
                if ( tuple[i] == null ) {
                    continue;
                }

                if ( "ID".equalsIgnoreCase(aliases[i]) ){
                    clinicaVo.setId(java.lang.Long.valueOf(tuple[i].toString()));
                    p.setIdClinicaAtual(clinicaVo.getId());
                }else if ( "NOME_CLINICA".equalsIgnoreCase(aliases[i]) ){
                    clinicaVo.setNome(tuple[i].toString());
                }else if ( "TELEFONE".equalsIgnoreCase(aliases[i])){
                    clinicaVo.setTelefone(tuple[i].toString());
                }else if ( "ID_PROFISSIONAL".equalsIgnoreCase(aliases[i])){
                    p.setId(java.lang.Long.valueOf(tuple[i].toString()));
                }else if ( "NOME_PROFISSIONAL".equalsIgnoreCase(aliases[i])){
                    p.setNome(tuple[i].toString());
                }else if ( "NUMERO_REGISTRO".equalsIgnoreCase(aliases[i])){
                    p.setNumeroRegistro(Integer.valueOf(tuple[i].toString()));
                }else if ( "NOME_ESPECIALIDADE".equalsIgnoreCase(aliases[i])){
                    p.setEspec(tuple[i].toString());
                }else if ( "ENDERECO".equalsIgnoreCase(aliases[i])){
                    p.setEndereco(tuple[i].toString());
                }else if ( "LATITUDE".equalsIgnoreCase(aliases[i])){
                    p.setLatitude(Double.valueOf(tuple[i].toString()));
                }else if ( "LONGITUDE".equalsIgnoreCase(aliases[i])){
                    p.setLongitude(Double.valueOf(tuple[i].toString()));
                }else if ( "CELULAR".equalsIgnoreCase(aliases[i])){
                    p.setTelefone(tuple[i].toString());
                }else if ( "ID_PROFISSAO".equalsIgnoreCase(aliases[i])){
                    p.setIdProfissao(Integer.valueOf(tuple[i].toString()));
                }else if ( "NOME_PROFISSAO".equalsIgnoreCase(aliases[i])){
                    p.setNomeProfissao(tuple[i].toString());
                }else if ( "ID_PARCEIRO".equalsIgnoreCase(aliases[i])){
                    p.setIdParceiro(Integer.valueOf(tuple[i].toString()));
                }else if ( "ACEITA_PARTICULAR".equalsIgnoreCase(aliases[i])){
                    p.setAceitaParticular(Integer.valueOf(tuple[i].toString()) == 1);
                }else if ( "TEM_AGENDA".equalsIgnoreCase(aliases[i])){
                    p.setTemAgenda(Integer.valueOf(tuple[i].toString()) == 1);
                }else if ( "ACEITA_PLANO".equalsIgnoreCase(aliases[i])){
                    p.setAceitaPlano(Integer.valueOf(tuple[i].toString()) == 1);
                }
            }
            return clinicaVo;
        }

        @Override
        public List transformList(List collection) {
            return collection;
        }
    }
}


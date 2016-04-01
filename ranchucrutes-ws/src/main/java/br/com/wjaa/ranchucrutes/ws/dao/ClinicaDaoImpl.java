package br.com.wjaa.ranchucrutes.ws.dao;

import br.com.wjaa.ranchucrutes.commons.vo.LocationVo;
import br.com.wjaa.ranchucrutes.framework.dao.GenericDaoImpl;
import br.com.wjaa.ranchucrutes.ws.entity.ClinicaEntity;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissionalEntity;
import org.hibernate.Query;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;

/**
 * Created by wagner on 4/1/16.
 */
public class ClinicaDaoImpl extends GenericDaoImpl<ClinicaEntity, Long> implements ClinicaDao  {


    @Resource(name = "findClinicaQuery")
    private String findClinicaQuery;

    @Resource(name = "findClinicaByEspecialidadeQuery")
    private String findClinicaByEspecialidadeQuery;

    @Override
    public List<ClinicaEntity> findClinica(Integer idEspecialidade, Integer idConvenio, LocationVo location, double raioPrecisao) {


        String [] nameParams = new String[]{"maxLatitude","minLatitude","maxLongitude","minLongitude",
                "idEspecialidade","idConvenio"};

        Object [] values = new Object[]{
                location.getMaxLatitude(raioPrecisao),
                location.getMinLatitude(raioPrecisao),
                location.getMaxLongitude(raioPrecisao),
                location.getMinLongitude(raioPrecisao),
                idEspecialidade,
                idConvenio};

        return this.find(findClinicaQuery, nameParams, values);

    }

    @Override
    public List<ClinicaEntity> findClinicaByEspecialidade(Integer idEspecialidade, LocationVo location, double raioPrecisao) {

        String [] nameParams = new String[]{"maxLatitude","minLatitude","maxLongitude","minLongitude",
                "idEspecialidade","aceitaParticular"};
         Object[] values = new Object[]{
                        location.getMaxLatitude(raioPrecisao),
                        location.getMinLatitude(raioPrecisao),
                        location.getMaxLongitude(raioPrecisao),
                        location.getMinLongitude(raioPrecisao),
                        idEspecialidade,
                        true};

        return this.find(findClinicaByEspecialidadeQuery,nameParams,values);
    }


    private List<ClinicaEntity> find(String query, String[] nameParams, Object[] values){
        Query q = this.getSession().createSQLQuery(query);
        for(int i = 0; i < nameParams.length; i ++ ){
            q.setParameter(nameParams[i],values[i]);
        }
        return q.list();
    }
}

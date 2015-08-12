package br.com.wjaa.ranchucrutes.ws.dao;

import br.com.wjaa.ranchucrutes.commons.vo.LocationVo;
import br.com.wjaa.ranchucrutes.ws.entity.MedicoEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by wagner on 17/06/15.
 */
@Repository
public class MedicoDaoImpl extends GenericDaoImpl<MedicoEntity, Long> implements MedicoDao {


    @Override
    public List<MedicoEntity> findMedico(Integer idEspecialidade, Integer idConvenio, LocationVo location,
                                         double raioPrecisao) {

        //TODO EXTERNAR OS SQLS DO PROJETO SE POSSIVEL.
        //TODO TÁ NA CARA QUE ESSA QUERY PRECISA SER MELHORADA TAMBÉM
        StringBuilder sb = new StringBuilder();
        sb.append(" select distinct m from " + MedicoEntity.class.getSimpleName() + " m ");
        sb.append(" join m.clinicas cs ");
        sb.append(" join cs.clinica cl ");
        sb.append(" join cl.endereco e ");
        sb.append(" join m.especialidades esp");
        sb.append(" join cl.convenios c");
        sb.append(" where e.latitude < :maxLatitude and e.latitude > :minLatitude ");
        sb.append(" and e.longitude < :maxLongitude and e.longitude > :minLongitude ");
        sb.append(" and esp.id = :idEspecialidade ");
        sb.append(" and c.id = :idConvenio ");

        String [] nameParams = new String[]{"maxLatitude","minLatitude","maxLongitude","minLongitude",
                "idEspecialidade","idConvenio"};

        return (List<MedicoEntity>) this.getHibernateTemplate().findByNamedParam(sb.toString(), nameParams,
                new Object[]{
                        location.getMaxLatitude(raioPrecisao),
                        location.getMinLatitude(raioPrecisao),
                        location.getMaxLongitude(raioPrecisao),
                        location.getMinLongitude(raioPrecisao),
                        idEspecialidade,
                        idConvenio});

    }

    @Override
    public List<MedicoEntity> findMedicoByEspecialidade(Integer idEspecialidade, LocationVo location,
                                         double raioPrecisao) {

        //TODO EXTERNAR OS SQLS DO PROJETO SE POSSIVEL.
        //TODO TÁ NA CARA QUE ESSA QUERY PRECISA SER MELHORADA TAMBÉM
        StringBuilder sb = new StringBuilder();
        sb.append(" select distinct m from " + MedicoEntity.class.getSimpleName() + " m ");
        sb.append(" join m.clinicas cs ");
        sb.append(" join cs.clinica cl ");
        sb.append(" join cl.endereco e ");
        sb.append(" join m.especialidades esp");
        sb.append(" where e.latitude < :maxLatitude and e.latitude > :minLatitude ");
        sb.append(" and e.longitude < :maxLongitude and e.longitude > :minLongitude ");
        sb.append(" and esp.id = :idEspecialidade ");
        sb.append(" and cs.aceitaParticular = :aceitaParticular ");
        sb.append(" order by m ");

        String [] nameParams = new String[]{"maxLatitude","minLatitude","maxLongitude","minLongitude",
                "idEspecialidade","aceitaParticular"};

        return (List<MedicoEntity>) this.getHibernateTemplate().findByNamedParam(sb.toString(), nameParams,
                new Object[]{
                        location.getMaxLatitude(raioPrecisao),
                        location.getMinLatitude(raioPrecisao),
                        location.getMaxLongitude(raioPrecisao),
                        location.getMinLongitude(raioPrecisao),
                        idEspecialidade,
                        true});

    }

    @Override
    public MedicoEntity getMedicoByCrm(Integer crm) {
        StringBuilder sql = new StringBuilder();
        sql.append("select m from " + MedicoEntity.class.getSimpleName() + " m ");
        sql.append(" where m.crm = :crm ");
        List<MedicoEntity> listMedicos = (List<MedicoEntity>) this.getHibernateTemplate()
                .findByNamedParam(sql.toString(), "crm", crm);

        if (!CollectionUtils.isEmpty(listMedicos)){
            return listMedicos.get(0);
        }
        return null;
    }

    @Override
    public MedicoEntity getMedicoByEmail(String email) {
        StringBuilder sql = new StringBuilder();
        sql.append("select m from " + MedicoEntity.class.getSimpleName() + " m ");
        sql.append(" where m.email = :email ");
        List<MedicoEntity> listMedicos = (List<MedicoEntity>) this.getHibernateTemplate()
                .findByNamedParam(sql.toString(), "email", email);

        if (!CollectionUtils.isEmpty(listMedicos)){
            return listMedicos.get(0);
        }
        return null;
    }
}

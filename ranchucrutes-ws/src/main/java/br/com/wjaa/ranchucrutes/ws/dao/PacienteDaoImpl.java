package br.com.wjaa.ranchucrutes.ws.dao;

import br.com.wjaa.ranchucrutes.framework.dao.GenericDaoImpl;
import br.com.wjaa.ranchucrutes.ws.entity.RedeSocialEnum;
import br.com.wjaa.ranchucrutes.ws.entity.PacienteEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wagner on 11/09/15.
 */
@Repository
public class PacienteDaoImpl extends GenericDaoImpl<PacienteEntity,Long> implements PacienteDao {


    @Override
    public PacienteEntity getPacienteByKeySocial(RedeSocialEnum redeSocial, String keySocial) {
        StringBuilder sb = new StringBuilder();
        sb.append("select p from " + PacienteEntity.class.getSimpleName() + " p ");
        sb.append(" where p.redeSocial = ? and p.keySocial = ?");
        List<?> resultList = getHibernateTemplate().find(sb.toString(), redeSocial, keySocial);

        if (resultList.size() > 0){
            return (PacienteEntity) resultList.get(0);
        }
        return null;
    }


    @Override
    public PacienteEntity getPacienteByEmail(String email) {
        StringBuilder sb = new StringBuilder();
        sb.append("select p from " + PacienteEntity.class.getSimpleName() + " p ");
        sb.append(" where p.email = ?");
        List<?> resultList = getHibernateTemplate().find(sb.toString(), email);

        if (resultList.size() > 0){
            return (PacienteEntity) resultList.get(0);
        }
        return null;
    }
}

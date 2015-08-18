package br.com.wjaa.ranchucrutes.ws.dao;

import br.com.wjaa.ranchucrutes.ws.entity.LoginEntity;
import br.com.wjaa.ranchucrutes.ws.entity.MedicoEntity;

import java.util.List;

/**
 * Created by wagner on 18/08/15.
 */
public class LoginDaoImpl extends GenericDaoImpl<LoginEntity,Integer> implements LoginDao {


    public MedicoEntity autenticarMedico(String email, String senha){
        StringBuilder sb = new StringBuilder();
        sb.append("from " + MedicoEntity.class.getSimpleName() + " m ");
        sb.append(" where m.email = ?  and m.senha = ? ");

        List<MedicoEntity> result = (List<MedicoEntity>) this.getHibernateTemplate().find(sb.toString(), email, senha);
        if (result.size() > 0){
            return result.get(0);
        }
        return null;
    }

    public MedicoEntity autenticarMedico(Integer crm, String senha){
        StringBuilder sb = new StringBuilder();
        sb.append("from " + MedicoEntity.class.getSimpleName() + " m ");
        sb.append(" where m.crm = ?  and m.senha = ? ");

        List<MedicoEntity> result = (List<MedicoEntity>) this.getHibernateTemplate().find(sb.toString(), crm, senha);
        if (result.size() > 0){
            return result.get(0);
        }
        return null;
    }

}

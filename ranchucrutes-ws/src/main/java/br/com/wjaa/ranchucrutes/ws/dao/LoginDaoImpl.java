package br.com.wjaa.ranchucrutes.ws.dao;

import br.com.wjaa.ranchucrutes.ws.entity.LoginEntity;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissionalEntity;
import br.com.wjaa.ranchucrutes.ws.entity.PacienteEntity;
import br.com.wjaa.ranchucrutes.ws.entity.RedeSocialEnum;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wagner on 18/08/15.
 */
@Repository
public class LoginDaoImpl extends GenericDaoImpl<LoginEntity,Integer> implements LoginDao {


    public ProfissionalEntity autenticarProfissional(String email, String senha){
        StringBuilder sb = new StringBuilder();
        sb.append("from " + ProfissionalEntity.class.getSimpleName() + " m ");
        sb.append(" where m.email = ?  and m.senha = ? ");

        List<ProfissionalEntity> result = (List<ProfissionalEntity>) this.getHibernateTemplate().find(sb.toString(), email, senha);
        if (result.size() > 0){
            return result.get(0);
        }
        return null;
    }

    public ProfissionalEntity autenticarProfissional(Integer crm, String senha){
        StringBuilder sb = new StringBuilder();
        sb.append("from " + ProfissionalEntity.class.getSimpleName() + " m ");
        sb.append(" where m.crm = ?  and m.senha = ? ");

        List<ProfissionalEntity> result = (List<ProfissionalEntity>) this.getHibernateTemplate().find(sb.toString(), crm, senha);
        if (result.size() > 0){
            return result.get(0);
        }
        return null;
    }

    @Override
    public PacienteEntity autenticarPaciente(String login, String senha) {
        StringBuilder sb = new StringBuilder();
        sb.append("from " + PacienteEntity.class.getSimpleName() + " p ");
        sb.append(" where p.email = ?  and p.senha = ? ");

        List<PacienteEntity> result = (List<PacienteEntity>) this.getHibernateTemplate().find(sb.toString(), login, senha);
        if (result.size() > 0){
            return result.get(0);
        }
        return null;

    }

    @Override
    public PacienteEntity autenticarPaciente(RedeSocialEnum redeSocial, String keySocial) {
        StringBuilder sb = new StringBuilder();
        sb.append("from " + PacienteEntity.class.getSimpleName() + " p ");
        sb.append(" where p.redeSocial = ?  and p.keySocial = ? ");

        List<PacienteEntity> result = (List<PacienteEntity>) this.getHibernateTemplate().find(sb.toString(),
                redeSocial, keySocial);
        if (result.size() > 0){
            return result.get(0);
        }
        return null;
    }

}

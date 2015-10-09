package br.com.wjaa.ranchucrutes.ws.dao;

import br.com.wjaa.ranchucrutes.ws.entity.LoginEntity;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissionalEntity;
import br.com.wjaa.ranchucrutes.ws.entity.PacienteEntity;
import br.com.wjaa.ranchucrutes.ws.entity.RedeSocialEnum;

/**
 * Created by wagner on 18/08/15.
 */
public interface LoginDao extends GenericDao<LoginEntity,Integer>{

    ProfissionalEntity autenticarProfissional(String email, String senha);

    ProfissionalEntity autenticarProfissional(Integer crm, String senha);

    PacienteEntity autenticarPaciente(String login, String senha);

    PacienteEntity autenticarPaciente(RedeSocialEnum redeSocial, String keySocial);
}

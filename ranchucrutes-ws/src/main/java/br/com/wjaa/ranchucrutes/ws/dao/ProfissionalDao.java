package br.com.wjaa.ranchucrutes.ws.dao;

import br.com.wjaa.ranchucrutes.commons.vo.LocationVo;
import br.com.wjaa.ranchucrutes.framework.dao.GenericDao;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissionalOrigemEntity;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissionalEntity;

import java.util.List;

/**
 * Created by wagner on 17/06/15.
 */
public interface ProfissionalDao extends GenericDao<ProfissionalEntity,Long> {

    /**
     *
     * @param idEspecialidade
     * @param idConvenio
     * @param location
     * @param raioPrecisao
     * @return
     */
    List<ProfissionalEntity> findProfissional(Integer idEspecialidade, Integer idConvenio, LocationVo location, double raioPrecisao);

    /**
     *
     * @param idEspecialidade
     * @param location
     * @param raioPrecisao
     * @return
     */
    List<ProfissionalEntity> findProfissionalByEspecialidade(Integer idEspecialidade, LocationVo location,
                                                             double raioPrecisao);

    /**
     *
     * @param numeroRegistro
     * @return
     */
    ProfissionalEntity getProfissionalByNumeroRegistro(String numeroRegistro);

    /**
     *
     * @param email
     * @return
     */
    ProfissionalEntity getProfissionalByEmail(String email);


    ProfissionalEntity getProfissionalByIdAndCategoria(Long idProfissional, Long idClinica, Integer[] idsCategoria);

    /**
     *
     * @param idProfissional
     * @param idClinica
     * @return
     */
    ProfissionalOrigemEntity findProfissionalOrigem(Long idProfissional, Long idClinica);

    /**
     *
     * @param startName
     * @return
     */
    List<ProfissionalEntity> findProfissionalByStartName(String startName);
}

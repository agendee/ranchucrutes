package br.com.wjaa.ranchucrutes.ws.dao;

import br.com.wjaa.ranchucrutes.commons.vo.LocationVo;
import br.com.wjaa.ranchucrutes.ws.entity.ProfissionalEntity;

import java.util.List;

/**
 * Created by wagner on 17/06/15.
 */
public interface ProfissionalDao extends GenericDao<ProfissionalEntity,Long>{

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
     * @param crm
     * @return
     */
    ProfissionalEntity getProfissionalByCrm(Integer crm);

    /**
     *
     * @param email
     * @return
     */
    ProfissionalEntity getProfissionalByEmail(String email);
}

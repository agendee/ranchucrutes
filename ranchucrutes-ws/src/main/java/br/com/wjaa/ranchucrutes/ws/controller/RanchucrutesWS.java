package br.com.wjaa.ranchucrutes.ws.controller;

import br.com.wjaa.ranchucrutes.commons.vo.ConvenioCategoriaVo;
import br.com.wjaa.ranchucrutes.ws.adapter.RanchucrutesAdapter;
import br.com.wjaa.ranchucrutes.ws.entity.ConvenioEntity;
import br.com.wjaa.ranchucrutes.ws.entity.ConvenioCategoriaEntity;
import br.com.wjaa.ranchucrutes.ws.entity.EspecialidadeEntity;
import br.com.wjaa.ranchucrutes.ws.service.RanchucrutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wagner on 18/06/15.
 */
@RestController
public class RanchucrutesWS extends BaseWS {


    @Autowired
    private RanchucrutesService ranchucrutesService;

    @RequestMapping(value = "/convenio/all", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8")
    public
    List<ConvenioEntity> listAllConvenio() {
        return this.ranchucrutesService.listAll(ConvenioEntity.class);
    }

    @RequestMapping(value = "/convenio/categoria/all", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8")
    public
    List<ConvenioCategoriaVo> listAllConvenioCategorias() {
        List<ConvenioCategoriaEntity> categorias = this.ranchucrutesService.listAll(ConvenioCategoriaEntity.class);
        return RanchucrutesAdapter.toConvenioCategoriaVo(categorias);
    }


    @RequestMapping(value = "/convenio/categoria/{idConvenio}", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8")
    public
    List<ConvenioCategoriaEntity> getCategoriasByIdConvenio(@PathVariable Integer idConvenio) {
        return this.ranchucrutesService.getByProperties(ConvenioCategoriaEntity.class,"idConvenio", idConvenio);
    }

    @RequestMapping(value = "/especialidade/all", produces = MediaType.APPLICATION_JSON_VALUE+ ";charset=UTF-8")
    public
    List<EspecialidadeEntity> listAllEspecialidade() {
        return this.ranchucrutesService.listAll(EspecialidadeEntity.class);
    }

}

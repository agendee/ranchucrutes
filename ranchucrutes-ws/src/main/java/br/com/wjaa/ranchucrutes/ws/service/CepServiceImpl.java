package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.commons.vo.LocationVo;
import br.com.wjaa.ranchucrutes.ws.adapter.RanchucrutesAdapter;
import br.com.wjaa.ranchucrutes.ws.dao.RanchucrutesDao;
import br.com.wjaa.ranchucrutes.ws.entity.CacheCep;
import br.com.wjaa.ranchucrutes.ws.entity.EnderecoEntity;
import br.com.wjaa.ranchucrutes.ws.exception.CepNotFoundException;
import br.com.wjaa.ranchucrutes.ws.exception.RestResponseUnsatisfiedException;
import br.com.wjaa.ranchucrutes.ws.exception.RestException;
import br.com.wjaa.ranchucrutes.ws.exception.RestRequestUnstable;
import br.com.wjaa.ranchucrutes.ws.rest.RestUtils;
import br.com.wjaa.ranchucrutes.commons.vo.EnderecoVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wagner on 16/06/15.
 */
@Service
public class CepServiceImpl implements CepService {

    private static final Log LOG = LogFactory.getLog(CepService.class);
    private final RedundanceApi redundanceApi = new CepServiceImpl.RedundanceApi();

    @Autowired
    private RanchucrutesDao dao;

    @Override
    public EnderecoEntity find(String cep) throws CepNotFoundException {
        LOG.debug("m=find cep=" +cep);

        for (CepApiUrl api : redundanceApi.getApis(cep)){
            try {
                LOG.debug("Tentando na primeira api = " + api.url);
                EnderecoVo e = this.findWithoutTryCatch(api);
                return RanchucrutesAdapter.fromEnderecoVo(e);
            } catch (RestResponseUnsatisfiedException e) {
                LOG.error("Nenhum endereco encontrado para o CEP:" + cep, e);
                LOG.error("Iniciando tentativa na proxima api...");
                continue;
            } catch (RestRequestUnstable | RestException e) {
                LOG.error("Erro ao buscar o cep + " + cep + ", tentando novamente, na mesma api " + api.url, e);

                try{
                    Thread.sleep(300l);
                    EnderecoVo end = this.findWithoutTryCatch(api);
                    return RanchucrutesAdapter.fromEnderecoVo(end);
                }catch(Exception ex){
                    LOG.error("Erro na segunda tentativa, iniciando busca na proxima api.", ex);
                }
            }
        }
        return null;
    }

    @Override
    public LocationVo getLocationByCep(String cep) {
        List<CacheCep> resultList = dao.getByProperties(CacheCep.class,"cep",cep);
        if (!CollectionUtils.isEmpty(resultList)){
            CacheCep cache = resultList.get(0);
            return new LocationVo(cache.getLatitude(),cache.getLongitude());
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveLocation(LocationVo location, String cep) {
        CacheCep cacheCep = new CacheCep();
        List<CacheCep> resultList = dao.getByProperties(CacheCep.class,"cep",cep);
        if (!CollectionUtils.isEmpty(resultList)){
            LOG.warn("Cep " + cep + " já está no cache, atualizando apenas por desencargo.");
            cacheCep = resultList.get(0);
        }
        cacheCep.setCep(cep);
        cacheCep.setLatitude(location.getLatitude());
        cacheCep.setLongitude(location.getLongitude());
        dao.save(cacheCep);
    }

    private EnderecoVo findWithoutTryCatch(CepApiUrl api) throws RestResponseUnsatisfiedException, RestRequestUnstable, RestException {
        return RestUtils.getJsonWithParamPath(EnderecoVo.class,api.url,api.uri );
    }


    class RedundanceApi{
        public List<CepApiUrl> getApis(String cep){
            List<CepApiUrl> apis = new ArrayList<>(2);
            apis.add(new CepApiUrl("apps.widenet.com.br","busca-cep/api/cep/{cep}.json".replace("{cep}",cep)));
            apis.add(new CepApiUrl("viacep.com.br","ws/{cep}/json/".replace("{cep}",cep)));
            apis.add(new CepApiUrl("api.postmon.com.br","v1/cep/{cep}".replace("{cep}",cep)));

            return apis;
        }
    }
    class CepApiUrl{
        String url;
        String uri;
        CepApiUrl(String url, String uri){
            this.uri = uri;
            this.url = url;
        }
    }

}

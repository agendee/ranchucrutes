package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.commons.vo.LocationVo;
import br.com.wjaa.ranchucrutes.ws.entity.EnderecoEntity;
import br.com.wjaa.ranchucrutes.ws.exception.DistanceNotFoundException;
import br.com.wjaa.ranchucrutes.ws.exception.LocationDuplicateFoundException;
import br.com.wjaa.ranchucrutes.ws.exception.LocationNotFoundException;
import br.com.wjaa.ranchucrutes.commons.vo.DistanceVo;

import java.util.List;

/**
 * Created by wagner on 15/06/15.
 */
public interface GoogleMapsService {

    /**
     *
     * @param address
     * @return Location
     */
    LocationVo getLatLngByAddress(String address) throws LocationNotFoundException, LocationDuplicateFoundException;

    /**
     *
     * @param origin
     * @param dests
     * @return
     * @throws DistanceNotFoundException
     */
    List<DistanceVo> getDistance(LocationVo origin, LocationVo ... dests) throws DistanceNotFoundException;


    /**
     *
     * @param enderecoEntity
     * @return
     */
    String patternAddress(EnderecoEntity enderecoEntity);
}

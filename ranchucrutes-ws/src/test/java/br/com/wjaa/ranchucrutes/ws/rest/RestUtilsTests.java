package br.com.wjaa.ranchucrutes.ws.rest;

import br.com.wjaa.ranchucrutes.commons.vo.LocationVo;
import br.com.wjaa.ranchucrutes.ws.entity.ClinicaEntity;
import br.com.wjaa.ranchucrutes.ws.entity.EnderecoEntity;
import br.com.wjaa.ranchucrutes.ws.exception.*;
import br.com.wjaa.ranchucrutes.ws.service.GoogleMapsServiceImpl;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by wagner on 16/06/15.
 */
public class RestUtilsTests {


    @Test
    @Ignore
    public void testGet(){

        try {
            ClinicaEntity endereco = RestUtils.getJsonWithParamPath(ClinicaEntity.class, "cep.correiocontrol.com.br", "07020280.json");
            Assert.assertNotNull(endereco);
            GoogleMapsServiceImpl gmp = new GoogleMapsServiceImpl();
            EnderecoEntity e = new EnderecoEntity();
            e.setBairro("Vila Paulista");
            e.setNumero(252);
            e.setUf("SP");
            e.setLogradouro("Rua são vicente");
            e.setLocalidade("Guarulhos");
            String end = gmp.patternAddress(e);
            System.out.println(end);
            LocationVo vo = gmp.getLatLngByAddress(end);
            System.out.println(vo);
            System.out.println(gmp.getDistance(vo, new LocationVo(-23.680441, -46.629099), new LocationVo(-23.495095, -46.611727), new LocationVo(-23.482658, -46.587437)));
        } catch (RestResponseUnsatisfiedException e) {
            Assert.fail(e.getMessage());
        } catch (RestException e) {
            Assert.fail(e.getMessage());
        } catch (RestRequestUnstable e) {
            Assert.fail(e.getMessage());
        } catch (LocationNotFoundException e) {
            e.printStackTrace();
        } catch (LocationDuplicateFoundException e) {
            e.printStackTrace();
        } catch (DistanceNotFoundException e) {
            e.printStackTrace();
        }


    }

}

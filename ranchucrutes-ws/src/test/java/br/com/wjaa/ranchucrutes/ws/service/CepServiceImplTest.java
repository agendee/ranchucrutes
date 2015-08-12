package br.com.wjaa.ranchucrutes.ws.service;

import br.com.wjaa.ranchucrutes.ws.entity.ClinicaEntity;
import br.com.wjaa.ranchucrutes.ws.entity.EnderecoEntity;
import org.junit.Test;

/**
 * Created by wagner on 16/06/15.
 */

public class CepServiceImplTest {

    @Test
    public void testFind() throws Exception {
        EnderecoEntity enderecoEntity = new CepServiceImpl().find("07020280");
        System.out.println("bairro: " + enderecoEntity.getBairro());
        System.out.println("cep: " + enderecoEntity.getCep());
        System.out.println("localidade: " + enderecoEntity.getLocalidade());
        System.out.println("logradouro: " + enderecoEntity.getLogradouro());
        System.out.println("uf: " + enderecoEntity.getUf());
    }
}
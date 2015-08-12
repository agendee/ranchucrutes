package br.com.wjaa.ranchucrutes.ws.exception;

/**
 * Created by wagner on 15/06/15.
 */
public class LocationNotFoundException extends Exception {

    private String addressNotFound;

    public LocationNotFoundException(String address) {
        this.addressNotFound = address;
    }

    public String getAddressNotFound() {
        return addressNotFound;
    }
}

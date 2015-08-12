package br.com.wjaa.ranchucrutes.ws.exception;

/**
 * Created by wagner on 15/06/15.
 */
public class LocationDuplicateFoundException extends Exception{

    private String addressDuplicate;

    /**
     *
     * @param address
     */
    public LocationDuplicateFoundException(String address, String msg) {
        super(msg);
        this.addressDuplicate = address;
    }

    public String getAddressDuplicate() {
        return addressDuplicate;
    }
}

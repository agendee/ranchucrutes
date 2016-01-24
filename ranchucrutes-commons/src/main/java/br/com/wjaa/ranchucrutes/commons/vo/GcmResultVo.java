package br.com.wjaa.ranchucrutes.commons.vo;

import java.util.List;

/**
 * Created by wagner on 24/01/16.
 */
public class GcmResultVo {

    private String multicast_id;
    private Integer success;
    private Integer failure;
    private Integer canonical_ids;
    private List<GcmMessageVo> results;

    public String getMulticast_id() {
        return multicast_id;
    }

    public void setMulticast_id(String multicast_id) {
        this.multicast_id = multicast_id;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getFailure() {
        return failure;
    }

    public void setFailure(Integer failure) {
        this.failure = failure;
    }

    public Integer getCanonical_ids() {
        return canonical_ids;
    }

    public void setCanonical_ids(Integer canonical_ids) {
        this.canonical_ids = canonical_ids;
    }

    public List<GcmMessageVo> getResults() {
        return results;
    }

    public void setResults(List<GcmMessageVo> results) {
        this.results = results;
    }
}

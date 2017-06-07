package com.backbase.model;

import java.util.List;

/**
 * Created by chauhan on 6/6/17.
 */
public class GeocodeResponse {

    public String status;

    public GeocodeResponse(String status) {
        this.status = status;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

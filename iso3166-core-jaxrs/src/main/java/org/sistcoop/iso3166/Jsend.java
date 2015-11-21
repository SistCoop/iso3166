package org.sistcoop.iso3166;

import java.io.Serializable;

public class Jsend implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private transient Object id;

    private Jsend() {

    }

    public static Jsend getSuccessJSend(Object id) {
        Jsend jsend = new Jsend();
        jsend.setId(id);
        return jsend;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

}

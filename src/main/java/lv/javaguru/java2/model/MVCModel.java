package lv.javaguru.java2.model;

import java.util.Objects;

public class MVCModel {
    private String jspName;
    private Object data;

    public MVCModel(String jspName, Object data) {
        this.jspName = jspName;
        this.data = data;
    }

    public String getJspName() {
        return jspName;
    }

    public Object getData() {
        return data;
    }
}

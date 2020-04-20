package com.tecnositaf.centrobackend.dto;


import java.util.Date;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.utilities.DateUtility;

 

public class DTOAlert {

    private String idAlert;
    private String idDeviceFk;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;
    private Integer code;
    private String description;
    private int storageYears;

    public DTOAlert() {
    }

    public DTOAlert(String idAlert, String idDeviceFk, Date timestamp, Integer code, String description,
            int storageYears) {
        this.idAlert = idAlert;
        this.idDeviceFk = idDeviceFk;
        this.timestamp = timestamp;
        this.code = code;
        this.description = description;
        this.storageYears = storageYears;
    }

 

    public String getIdAlert() {
        return idAlert;
    }

 

    public void setIdAlert(String idAlert) {
        this.idAlert = idAlert;
    }

 

    public String getIdDeviceFk() {
        return idDeviceFk;
    }

 

    public void setIdDeviceFk(String idDeviceFk) {
        this.idDeviceFk = idDeviceFk;
    }

 

    public Date getTimestamp() {
        return timestamp;
    }

 

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

 

    public Integer getCode() {
        return code;
    }

 

    public void setCode(Integer code) {
        this.code = code;
    }

 

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStorageYears() {
        return storageYears;
    }

    public void setStorageYears(int storageYears) {
        this.storageYears = storageYears;
    }

    @Override
    public String toString() {
        return "DTOAlert{" + "idAlert=" + idAlert + ", idDeviceFk='" + idDeviceFk + '\'' + ", description='"
                + description + '\'' + ", code=" + code + ", timestamp='" + timestamp + '\'' + ", storageYears="
                + storageYears + '}';
    }

    public Alert toAlert() {
        Alert output = new Alert();
        BeanUtils.copyProperties(this, output);
        output.setTimestamp(DateUtility.convertDateToLocalDateTime(getTimestamp()));
        return output;
    }
}

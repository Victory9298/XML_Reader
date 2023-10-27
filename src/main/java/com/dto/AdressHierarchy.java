package com.dto;

import java.time.LocalDate;
import java.util.Objects;

public class AdressHierarchy {
    private String objectId;
    private String parentObjId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;

    public AdressHierarchy(String objectId, String parentObjId, LocalDate startDate, LocalDate endDate, Boolean isActive) {
        this.objectId = objectId;
        this.parentObjId = parentObjId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getParentObjId() {
        return parentObjId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdressHierarchy that)) return false;
        return Objects.equals(getObjectId(), that.getObjectId()) && Objects.equals(getParentObjId(), that.getParentObjId()) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getObjectId(), getParentObjId(), startDate, endDate, isActive);
    }
}

package com.dto;

import java.time.LocalDate;
import java.util.Objects;

public class Adress implements Comparable<Adress>{
    private String objectId;
    private String name;
    private String typeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActual;
    private boolean isActive;

    public Adress(String objectId, String name, String typeName, LocalDate startDate, LocalDate endDate, boolean isActual, boolean isActive) {
        this.objectId = objectId;
        this.name = name;
        this.typeName = typeName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActual = isActual;
        this.isActive = isActive;
    }
    public String getObjectId() {
        return objectId;
    }

    public String getTypeName() {
        return typeName;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public boolean isActual() {
        return isActual;
    }

    @Override
    public String toString() {
        return objectId + ": " + typeName + " " +  name;
    }

    public String toStringForTask2() {
        return typeName + " " +  name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adress adress)) return false;
        return isActual() == adress.isActual() && isActive == adress.isActive && Objects.equals(getObjectId(), adress.getObjectId()) && Objects.equals(name, adress.name) && Objects.equals(getTypeName(), adress.getTypeName()) && Objects.equals(getStartDate(), adress.getStartDate()) && Objects.equals(getEndDate(), adress.getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getObjectId(), name, getTypeName(), getStartDate(), getEndDate(), isActual(), isActive);
    }

    @Override
    public int compareTo(Adress other) {
        return this.objectId.compareTo(other.objectId);
    }
}

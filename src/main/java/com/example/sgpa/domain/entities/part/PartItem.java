package com.example.sgpa.domain.entities.part;

import java.util.Objects;

public class PartItem {
    private int patrimonialId;
    private StatusPart status;
    private String observation;
    private Part part;

    public String getType(){
        return part.getType();
    }

    public PartItem() {
    }

    public PartItem(StatusPart status, String observation, Part part) {
        this.status = status;
        this.observation = observation;
        this.part = part;
    }

    public PartItem(int patrimonialId, StatusPart status, String observation, Part part) {
        this.patrimonialId = patrimonialId;
        this.status = status;
        this.observation = observation;
        this.part = part;
    }

    public int getPatrimonialId() {
        return patrimonialId;
    }

    public void setPatrimonialId(int patrimonialId) {
        this.patrimonialId = patrimonialId;
    }

    public StatusPart getStatus() {
        return status;
    }

    public void setStatus(StatusPart status) {
        this.status = status;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartItem itemPart = (PartItem) o;
        return Objects.equals(patrimonialId, itemPart.patrimonialId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patrimonialId);
    }

    public boolean isAvailable() {
        return status == StatusPart.AVAILABLE;
    }
    public boolean isNotAvailable() {
        return status != StatusPart.AVAILABLE;
    }
}

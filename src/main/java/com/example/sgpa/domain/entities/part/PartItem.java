package com.example.sgpa.domain.entities.part;

import java.util.Objects;

public class PartItem {
    private String patrimonialId;
    private StatusPart status;
    private String situation;
    private Part part;

    public PartItem() {
    }

    public PartItem(StatusPart status, String situation, Part part) {
        this.status = status;
        this.situation = situation;
        this.part = part;
    }

    public PartItem(String patrimonialId, StatusPart status, String situation, Part part) {
        this.patrimonialId = patrimonialId;
        this.status = status;
        this.situation = situation;
        this.part = part;
    }

    public String getPatrimonialId() {
        return patrimonialId;
    }

    public void setPatrimonialId(String patrimonialId) {
        this.patrimonialId = patrimonialId;
    }

    public StatusPart getStatus() {
        return status;
    }

    public void setStatus(StatusPart status) {
        this.status = status;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
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
}

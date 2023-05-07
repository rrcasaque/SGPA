package com.example.sgpa.domain.entities.part;

import java.util.Objects;

public class ItemPart {
    private String PatrimonialId;
    private StatusPart status;
    private String situation;
    private Part part;

    public ItemPart() {
    }

    public ItemPart(StatusPart status, String situation, Part part) {
        this.status = status;
        this.situation = situation;
        this.part = part;
    }

    public ItemPart(String patrimonialId, StatusPart status, String situation, Part part) {
        PatrimonialId = patrimonialId;
        this.status = status;
        this.situation = situation;
        this.part = part;
    }

    public String getPatrimonialId() {
        return PatrimonialId;
    }

    public void setPatrimonialId(String patrimonialId) {
        PatrimonialId = patrimonialId;
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
        ItemPart itemPart = (ItemPart) o;
        return Objects.equals(PatrimonialId, itemPart.PatrimonialId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(PatrimonialId);
    }
}

package com.example.sgpa.domain.entities.checkout;

import com.example.sgpa.domain.entities.part.PartItem;

import java.util.Objects;

public class CheckedOutItemKey {
    private int checkoutId;
    private int partItemId;
    public CheckedOutItemKey(int  checkoutId, int partItemId) {
        this.checkoutId = checkoutId;
        this.partItemId = partItemId;
    }

    public int getCheckoutId() {
        return checkoutId;
    }

    public int getPartItemId() {
        return partItemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckedOutItemKey that = (CheckedOutItemKey) o;
        return checkoutId == that.checkoutId && partItemId == that.partItemId;
    }
    @Override
    public int hashCode() {
        return Objects.hash(checkoutId, partItemId);
    }
}

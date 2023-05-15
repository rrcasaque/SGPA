package com.example.sgpa.domain.entities.checkout;

import com.example.sgpa.domain.entities.part.ItemPart;

import java.util.Objects;

public class CheckedOutItemKey {
    private Checkout Checkout;
    private ItemPart itemPart;

    public CheckedOutItemKey(com.example.sgpa.domain.entities.checkout.Checkout checkout, ItemPart itemPart) {
        Checkout = checkout;
        this.itemPart = itemPart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckedOutItemKey that = (CheckedOutItemKey) o;
        return Objects.equals(Checkout, that.Checkout) && Objects.equals(itemPart, that.itemPart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Checkout, itemPart);
    }
}

package com.example.sgpa.domain.usecases.checkout;

import com.example.sgpa.domain.entities.checkout.CheckedOutItem;
import com.example.sgpa.domain.entities.checkout.Checkout;
import com.example.sgpa.domain.usecases.utils.DAO;

import java.util.List;

public interface CheckOutDAO extends DAO<Checkout, Integer>{

}

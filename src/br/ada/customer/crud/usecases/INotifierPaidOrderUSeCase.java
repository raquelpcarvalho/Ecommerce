package br.ada.customer.crud.usecases;

import br.ada.customer.crud.model.Order;

public interface INotifierPaidOrderUSeCase {
    void notify(Order order);
}

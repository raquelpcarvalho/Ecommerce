package br.ada.customer.crud.usecases;

import br.ada.customer.crud.model.Order;

public interface INotifierPendingPaymentUseCase {
    void notify(Order order);
}

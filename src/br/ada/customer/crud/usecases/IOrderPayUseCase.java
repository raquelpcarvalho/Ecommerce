package br.ada.customer.crud.usecases;

import br.ada.customer.crud.model.Order;

public interface IOrderPayUseCase {


    /*
     * 1 - Pedido precisa estar com status == OrderStatus.PENDING_PAYMENT
     * 2 - Pedido deve passar a ter o status igual a OrderStatus.PAID
     * 3 - Notificar o cliente sobre o pagamento com sucesso
     */
    void pay(Order order);

}

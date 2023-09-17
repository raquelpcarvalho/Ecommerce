package br.ada.customer.crud.usecases;

import br.ada.customer.crud.model.Order;

public interface IOrderShippingUseCase {

    /*
     * 1 - Entrega só pode acontecer depois do pedido ter sido pago (order.status == OrderStatus.PAID)
     * 2 - Pedido deve passar a ter o status igual a OrderStatus.FINISH
     * 3 - Notificar o cliente e agradecer pela compra
     */
    void shipping(Order order);

}

package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.INotifierPaidOrderUSeCase;
import br.ada.customer.crud.usecases.IOrderPayUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

public class OrderPayUseCaseImpl implements IOrderPayUseCase {

    /*
     * 1 - Pedido precisa estar com status == OrderStatus.PENDING_PAYMENT
     * 2 - Pedido deve passar a ter o status igual a OrderStatus.PAID
     * 3 - Notificar o cliente sobre o pagamento com sucesso
     */

    private OrderRepository orderRepository;

    private INotifierPaidOrderUSeCase notifier;

    public OrderPayUseCaseImpl(OrderRepository orderRepository, INotifierPaidOrderUSeCase notifier) {
        this.orderRepository = orderRepository;
        this.notifier = notifier;
    }

    @Override
    public void pay(Order order) {
        if(order.getStatus()!= OrderStatus.PENDING_PAYMENT){
            throw new IllegalStateException("Esse pedido ainda não pode ser pago!");
        }

        order.setStatus(OrderStatus.PAID);
        orderRepository.update(order);
        notifier.notify(order);
    }
}

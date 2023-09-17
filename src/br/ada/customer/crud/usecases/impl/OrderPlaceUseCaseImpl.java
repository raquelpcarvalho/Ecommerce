package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.INotifierPendingPaymentUseCase;
import br.ada.customer.crud.usecases.IOrderPlaceUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

import java.math.BigDecimal;

public class OrderPlaceUseCaseImpl implements IOrderPlaceUseCase {
    /*
     * 1 - Pedido precisa estar com status == OrderStatus.OPEN
     * 2 - Pedido precisa ter no mínimo um item
     * 3 - Valor deve ser maior que zero
     * 4 - Notificar o cliente que esta aguardando o pagamento
     * 5 - Pedido deve passar a ter o status igual OrderStatus.PENDING_PAYMENT
     */
    private OrderRepository orderRepository;
    private INotifierPendingPaymentUseCase notifier;

    public OrderPlaceUseCaseImpl(OrderRepository orderRepository, INotifierPendingPaymentUseCase notifier) {
        this.orderRepository = orderRepository;
        this.notifier = notifier;
    }

    @Override
    public void placeOrder(Order order) {

        BigDecimal sum = BigDecimal.ZERO;


        if (order.getStatus() != OrderStatus.OPEN){
            throw new RuntimeException("Pedido não está aberto!");
        }
        if (order.getItems() == null || order.getItems().isEmpty()){ // null = não existe carrinho (ñ inicializou a variável), empty = existe mas está vazia
            throw new RuntimeException("Não tem item no pedido");
        }
        for (OrderItem item: order.getItems()){
            sum = sum.add(item.getSaleValue());
        }

        if (sum.compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("A soma dos produtos é menor ou igual a 0!");
        }
        order.setStatus(OrderStatus.PENDING_PAYMENT);
        orderRepository.update(order);
        notifier.notify(order);


    }
}

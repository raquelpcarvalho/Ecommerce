package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.model.Product;
import br.ada.customer.crud.usecases.IOrderItemUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;
import br.ada.customer.crud.usecases.repository.ProductRepository;

import java.math.BigDecimal;

public class OrderItemUseCaseImpl implements IOrderItemUseCase {

    private OrderRepository repository;
    private ProductRepository productRepository;

    public OrderItemUseCaseImpl(OrderRepository repository, ProductRepository productRepository){
        this.repository = repository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderItem addItem(Order order, Product product, BigDecimal price, Integer amount) {
        /*
         * 1 - Pedido precisa estar com status == OrderStatus.OPEN
         * 2 - Trocar a quantidade que foi vendida desse produto
         * 3 - Lembrar de atualizar o banco através do repository
         */
        if (order.getStatus() != OrderStatus.OPEN) {
            throw new IllegalStateException("Não pode alterar esse pedido!");
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setSaleValue(price);
        orderItem.setAmount(amount);
        order.getItems().add(orderItem);
        repository.update(order);
        return orderItem;
    }

    @Override
    public OrderItem changeAmount(Order order, Product product, Integer amount) {
        /*
         * 1 - Pedido precisa estar com status == OrderStatus.OPEN
         * 2 - Lembrar de atualizar o banco através do repository
         */

        if (order.getStatus() != OrderStatus.OPEN) {
            throw new IllegalStateException("Não pode alterar esse pedido.");
        }

        OrderItem itemToChange = order.findOrderItemByProduct(product);

        if (itemToChange == null){
            throw new IllegalArgumentException("Produto não existe!");
        }
        itemToChange.setAmount(amount);
        repository.update(order);//save
        return itemToChange;
    }

    @Override
    public void removeItem(Order order, Product product) {
        if (order.getStatus() != OrderStatus.OPEN) {
            throw new IllegalStateException("Não pode alterar esse pedido!");
        }

        OrderItem itemRemove = findItemByProductInTheOrder(order,product);

        if (itemRemove == null){
            throw new IllegalArgumentException("Produto não existe no pedido!");
        }

        order.getItems().remove(itemRemove);

        repository.save(order);
    }

    private OrderItem findItemByProductInTheOrder(Order order, Product product) {
        for (OrderItem item : order.getItems()){
            if (item.getProduct().equals(product)){
                return item;
            }
        }
        return null;
    }
}

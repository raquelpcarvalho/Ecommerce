package br.ada.customer.crud.usecases;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.model.Product;

import java.math.BigDecimal;

public interface IOrderItemUseCase {

    /*
     * 1 - Pedido precisa estar com status == OrderStatus.OPEN
     * 2 - Lembrar de atualizar o banco através do repository
     */
    OrderItem addItem(Order order, Product product, BigDecimal price, Integer amount);

    /*
     * 1 - Pedido precisa estar com status == OrderStatus.OPEN
     * 2 - Trocar a quantidade que foi vendida desse produto
     * 3 - Lembrar de atualizar o banco através do repository
     */
    OrderItem changeAmount(Order order, Product product, Integer amount);

    /*
     * 1 - Pedido precisa estar com status == OrderStatus.OPEN
     * 2 - Lembrar de atualizar o banco através do repository
     */
    void removeItem(Order order, Product product);

}

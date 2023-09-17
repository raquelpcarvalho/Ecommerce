package br.ada.customer.crud.integration.email;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.IShippingNotifierUseCase;

public class ShippingEmailNotifierImpl implements IShippingNotifierUseCase {
    private SendEmail sendEmail;

    public ShippingEmailNotifierImpl(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Override
    public void notify(Order order) {
        sendEmail.send("comunicado@ada.com.br", order.getCustomer().getEmail(), "Pedido enviado!");
    }
}

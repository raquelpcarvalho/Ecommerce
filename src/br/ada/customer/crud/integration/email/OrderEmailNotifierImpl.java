package br.ada.customer.crud.integration.email;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.INotifierPaidOrderUSeCase;

public class OrderEmailNotifierImpl implements INotifierPaidOrderUSeCase {
    private SendEmail sendEmail;

    public OrderEmailNotifierImpl(SendEmail sendEmail){
        this.sendEmail = sendEmail;
    }

    @Override
    public void notify(Order order) {
        sendEmail.send("comunicado@ada.com.br", order.getCustomer().getEmail(), "Confirmamos o pagamento do seu pedido.");
    }
}

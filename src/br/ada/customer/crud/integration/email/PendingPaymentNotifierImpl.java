package br.ada.customer.crud.integration.email;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.INotifierPendingPaymentUseCase;

public class PendingPaymentNotifierImpl implements INotifierPendingPaymentUseCase {
    private SendEmail sendEmail;

    public PendingPaymentNotifierImpl(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Override
    public void notify(Order order) {
        sendEmail.send("comunicado@ada.com.br", order.getCustomer().getEmail(), "Aguardando o pagamento!");
    }
}

class EmailClient {
    static int count = 0;

    EmailClient() {
        count++;
        System.out.println("EmailClient created. Total now: " + count);
    }

    void send(String to) {
        System.out.println("sending to " + to);
    }
}

class OrderService {
    private EmailClient email = new EmailClient();
    void placeOrder() { email.send("customer@example.com"); }
}

class InvoiceService {
    private EmailClient email = new EmailClient();
    void sendInvoice() { email.send("accounts@example.com"); }
}

public class Step2 {
    public static void main(String[] args) {
        OrderService orders = new OrderService();
        InvoiceService invoices = new InvoiceService();

        orders.placeOrder();
        invoices.sendInvoice();
    }
}
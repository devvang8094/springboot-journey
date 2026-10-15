class EmailClient {
    void send(String to) {
        System.out.println("sending to " + to);
    }
}

class OrderService {
    private EmailClient email = new EmailClient();

    void placeOrder() {
        email.send("customer@example.com");
    }
}

public class Step1 {
    public static void main(String[] args) {
        OrderService service = new OrderService();
        service.placeOrder();
    }
}
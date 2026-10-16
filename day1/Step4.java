import java.util.HashMap;
import java.util.Map;

public class Step4 {
    public static void main(String[] args) {
        MiniContainer container = new MiniContainer();

        container.put(new EmailClient());
        container.put(new OrderService(container.get(EmailClient.class)));
        container.put(new InvoiceService(container.get(EmailClient.class)));
        container.put(new ReportService(container.get(EmailClient.class),
                                        container.get(OrderService.class)));

        container.get(OrderService.class).placeOrder();
        container.get(InvoiceService.class).sendInvoice();
        container.get(ReportService.class).sendReport();

        System.out.println("EmailClients created: " + EmailClient.count);
    }
}

class MiniContainer {
    private final Map<Class<?>, Object> instances = new HashMap<>();

    void put(Object instance) {
        instances.put(instance.getClass(), instance);
    }

    @SuppressWarnings("unchecked")
    <T> T get(Class<T> type) {
        Object found = instances.get(type);
        if (found == null) {
            throw new IllegalStateException("Nothing registered for " + type.getSimpleName());
        }
        return (T) found;
    }
}

class EmailClient {
    static int count = 0;
    EmailClient() { count++; }
    void send(String to) { System.out.println("sending to " + to); }
}

class OrderService {
    private final EmailClient email;
    OrderService(EmailClient email) { this.email = email; }
    void placeOrder() { email.send("customer@example.com"); }
}

class InvoiceService {
    private final EmailClient email;
    InvoiceService(EmailClient email) { this.email = email; }
    void sendInvoice() { email.send("accounts@example.com"); }
}

class ReportService {
    private final EmailClient email;
    private final OrderService orders;

    ReportService(EmailClient email, OrderService orders) {
        this.email = email;
        this.orders = orders;
    }

    void sendReport() {
        orders.placeOrder();
        email.send("devang8094@gmail.com");
    }
}
import java.util.HashMap;
import java.util.Map;

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

public class Step4 {
    public static void main(String[] args) {
        MiniContainer container = new MiniContainer();

        container.put(new EmailClient());
        container.put(new OrderService(container.get(EmailClient.class)));
        container.put(new InvoiceService(container.get(EmailClient.class)));
        container.put(new ReportService(container.get(EmailClient.class)));

        container.get(OrderService.class).placeOrder();
        container.get(InvoiceService.class).sendInvoice();

        container.get(ReportService.class).sendReport();
        System.out.println("EmailClients created: " + EmailClient.count);
    }
}
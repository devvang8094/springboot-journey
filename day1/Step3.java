class EmailClient {
    static int count = 0;
    EmailClient() {
        count++;
        System.out.println("EmailClient created. Total now: " + count);
    }
    void send(String to) { System.out.println("sending to " + to); }
}
class OrderService {
    private final EmailClient email;

    OrderService(EmailClient email) {   // handed in, not built
        this.email = email;
    }

    void placeOrder() { email.send("customer@example.com"); }
}

class InvoiceService {
    private final EmailClient email;

    InvoiceService(EmailClient email) {
        this.email = email;
    }

    void sendInvoice() { email.send("accounts@example.com"); }
}

class ReportService{
    private final EmailClient email;

    public ReportService(EmailClient email){
        this.email = email;
    }

    public void sendReport(){
        email.send("devang8094@gmail.com");
    }
}

public class Step3 {
    public static void main(String[] args) {
        EmailClient shared = new EmailClient();

        OrderService orders = new OrderService(shared);
        InvoiceService invoices = new InvoiceService(shared);
        
        orders.placeOrder();
        invoices.sendInvoice();
    }
}
package io.github.shniu.arts.design.lsp.v1;

import org.apache.http.client.HttpClient;

public class Main {

    public void send(Transporter transporter) {
        Request request = new Request();
        transporter.sendRequest(request);
    }

    public static void main(String[] args) {
        HttpClient httpClient = null;
        Transporter transporter = new Transporter(httpClient);

        Main demo = new Main();
        demo.send(transporter);

        // 这里就符合 LSP
        SecurityTransporter securityTransporter = new SecurityTransporter(httpClient, "", "");
        demo.send(securityTransporter);

        // 这个实现就不符合LSP
        NotValidSecurityTransporter notValidSecurityTransporter =
                new NotValidSecurityTransporter(httpClient, "", "");
        demo.send(notValidSecurityTransporter);
    }
}

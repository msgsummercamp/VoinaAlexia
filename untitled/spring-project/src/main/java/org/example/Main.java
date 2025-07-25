package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//        HelloService helloService = context.getBean(HelloService.class);
//        helloService.sayHello();
        Client client = context.getBean(Client.class);
        client.processMessage("Hello via Spring DI!");
    }
}
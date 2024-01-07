package com.example.productservice.controllers.webhooks;

import com.stripe.model.Event;
import com.stripe.net.Webhook;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhooks/stripe")
public class StripeWebhookController {
    @PostMapping("/")
    public void handleStripeWebhook(@RequestBody Event webhookEvent) {
        System.out.println("Stripe Webhook Received");
        return;
    }
}

package com.project.hb.billing.domain.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

/**
 * MESSAGE CHANNEL CONFIG
 */
public interface BillingMessageChannels {

    public final static String BOOKING_ORDER_INPUT = "bookingOrderInput";

    @Input(BOOKING_ORDER_INPUT)
    MessageChannel bookingOrderInput();

}

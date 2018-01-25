package com.project.hb.booking.domain.message;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * BOOKING MESSAGE CHANNEL
 */
@EnableBinding(BookingMessageChannels.class)
public interface BookingMessageChannels {

    public final static String BOOKING_ORDER_OUTPUT = "bookingOrderOutput";

    @Output(BOOKING_ORDER_OUTPUT)
    MessageChannel bookingOrderOutput();
}



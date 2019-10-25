package jms;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.Subscription;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.IOException;
import java.util.logging.Logger;

@MessageDriven(mappedName = "jms/dat250/Topic", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "topicSubscription = 'dweet'")})
public class DweetListener implements MessageListener {
    @Override
    public void onMessage(Message message) {

        try {
            Subscription subscription = message.getBody(Subscription.class);
            JsonObject json = new JsonObject();
            json.addProperty("User", subscription.getUser().getUsername());
            json.addProperty("Device", subscription.getDevice().getDeviceName());
            json.addProperty("SubID", subscription.getId());

            Logger logger = Logger.getLogger(getClass().getName());
            logger.info("DTWEET User" + subscription.getUser().getUsername());
            logger.info("DTWEET Device" + subscription.getDevice().getDeviceName());
            logger.info("DTWEET SubID" + subscription.getId());
            logger.info("DTWEET: Sending tweet to dweet...");
            logger.info("DTWEET JSON: " + json);
            try {
                DweetConnection dc = new DweetConnection();
                dc.publish(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JMSException e1) {
            e1.printStackTrace();
        }

    }

}
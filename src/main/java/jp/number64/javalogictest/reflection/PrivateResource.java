package jp.number64.javalogictest.reflection;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrivateResource implements PrivateResourceInterface {
    private static Logger LOGGER = LoggerFactory.getLogger(PrivateResource.class);

    private static final int BASE_MESSAGE_INDEX = 0;
    private static final int ADDITIONAL_MESSAGE_INDEX = 1;

    protected List<String> baseMessages = null;

    @Override
    public void setBaseMessages(List<String> baseMessages) {
        this.baseMessages = baseMessages;
    };

    @Override
    public List<String> getBaseMessages() {
        return baseMessages;
    };

    @Override
    public void prepareMessages(String replaceTarget, String replacedWord)
      throws NullPointerException, IndexOutOfBoundsException {
        // check1
        if (this.baseMessages == null) {
            throw new NullPointerException("NullCheck Error. BaseMessages obj is null.");
        }
        // check2
        if (this.baseMessages.size() < 2) {
            throw new IndexOutOfBoundsException("SizeCheck Error. BaseMessage needs two messages.");
        }
        //
        String baseMessage = this.baseMessages.get(BASE_MESSAGE_INDEX);
        this.baseMessages.set(BASE_MESSAGE_INDEX, baseMessage.replace(replaceTarget, replacedWord));
        LOGGER.debug("baseMessage: {}", baseMessages.get(BASE_MESSAGE_INDEX));
        //
        String additionalMessage = this.baseMessages.get(ADDITIONAL_MESSAGE_INDEX);
        this.baseMessages.set(ADDITIONAL_MESSAGE_INDEX, additionalMessage.replace(replaceTarget, replacedWord));
        LOGGER.debug("additionalMessage: {}", baseMessages.get(ADDITIONAL_MESSAGE_INDEX));
    }

    @Override
    public String outputMessages() {
        String replacedBaseMessage = this.baseMessages.get(BASE_MESSAGE_INDEX);
        String replacedAdMessage = this.baseMessages.get(ADDITIONAL_MESSAGE_INDEX);
        String result = replacedBaseMessage + " " + replacedAdMessage;
        LOGGER.debug("outputMessage: {}", result);
        return result;
    }
}

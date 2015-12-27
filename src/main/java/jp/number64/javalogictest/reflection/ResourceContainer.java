package jp.number64.javalogictest.reflection;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceContainer {
    private static Logger LOGGER = LoggerFactory.getLogger(ResourceContainer.class);
    private static final String SECRET_MESSAGE = "PRIVATE";

    private String baseMessage = "dummy";
    private String replaceTarget = "dummy";
    private String newWord = "dummy";

    private PrivateResourceInterface innerResource = null;

    public ResourceContainer(final String baseMessage, final String replaceTarget, final String newWord) {
        innerResource = new PrivateResource();
        this.baseMessage = baseMessage;
        this.replaceTarget = replaceTarget;
        this.newWord = newWord;
        LOGGER.debug("baseMessage  : {}", baseMessage);
        LOGGER.debug("replaceTarget: {}", replaceTarget);
        LOGGER.debug("newWord      : {}", newWord);
    }

    public String doSomething() {
        String result = doPrivateThings();
        return result;
    }

    /**  */
    private String doPrivateThings() {
        innerResource.setBaseMessages(createBaseMessages(SECRET_MESSAGE));
        innerResource.prepareMessages(replaceTarget, newWord);
        String result = innerResource.outputMessages();
        return result;
    }

    /**  */
    private List<String> createBaseMessages(String additionalMessage) {
        List<String> result = new ArrayList<>();
        result.add(baseMessage);
        result.add(additionalMessage);
        return result;
    }
}

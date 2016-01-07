package jp.number64.javalogictest.reflection;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceContainer {
    private static Logger LOGGER = LoggerFactory.getLogger(ResourceContainer.class);
    private static int SECRET_NUMBER = 64;
    private static String SECRET_MESSAGE = "PRIVATE";   // caution: for reflection-test, this isn't "final".
    private static final String ENCODE_TYPE_REV = "reverse";
    private static final String ENCODE_TYPE_CUT = "cut";

    private String baseMessage;
    private String replaceTarget;
    private String newWord;
    private int secretNumber;

    private PrivateResourceInterface innerResource = null;

    /**  */
    public ResourceContainer(final String baseMessage, final String replaceTarget, final String newWord) {
        this(baseMessage, replaceTarget, newWord, SECRET_NUMBER);
    }

    /** private constructor. SingletonPattern, etc... */
    private ResourceContainer(
      final String baseMessage, final String replaceTarget, final String newWord, final int secretNumber) {
        innerResource = new PrivateResource();
        this.baseMessage = baseMessage;
        this.replaceTarget = replaceTarget;
        this.newWord = newWord;
        this.secretNumber = secretNumber;
        LOGGER.debug("baseMessage  : {}", this.baseMessage);
        LOGGER.debug("replaceTarget: {}", this.replaceTarget);
        LOGGER.debug("newWord      : {}", this.newWord);
        LOGGER.debug("secretNumber : {}", this.secretNumber);
    }

    /**  */
    public static String getEncodedSecretMessage() {
        String result = doPrivateStaticEncode(ENCODE_TYPE_REV);
        return result;
    }

    /**  */
    private static String doPrivateStaticEncode(String encodingType) {
        String result = null;
        switch (encodingType) {
            case ENCODE_TYPE_REV:
                result = StringUtils.reverse(SECRET_MESSAGE);
                break;
            case ENCODE_TYPE_CUT:
                result = StringUtils.mid(SECRET_MESSAGE, 1, SECRET_MESSAGE.length() - 2);
                break;
            default:
                result = "";
        }
        return result;
    }

    /**  */
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

package jp.number64.javalogictest.reflection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrivateResourceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrivateResourceTest.class);

    @Test
    public void case01() {
        LOGGER.debug("PrivateResourceTest! ");
    }
}

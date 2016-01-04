package jp.number64.javalogictest.reflection;

import jp.number64.javalogictest.reflection.PrivateResourceTest.Pattern01PublicAccessTest;
import jp.number64.javalogictest.reflection.PrivateResourceTest.Pattern02ReflectionAccessTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Suite.class)
@SuiteClasses({
    Pattern01PublicAccessTest.class,
    Pattern02ReflectionAccessTest.class
})
public class PrivateResourceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrivateResourceTest.class);

    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public static final class Pattern01PublicAccessTest {
        private static final Logger LOGGER = LoggerFactory.getLogger(Pattern01PublicAccessTest.class);

        @Test
        public void case01() {
            LOGGER.debug("PrivateResourceTest! ");
        }
    }

    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public static final class Pattern02ReflectionAccessTest {
        private static final Logger LOGGER = LoggerFactory.getLogger(Pattern02ReflectionAccessTest.class);

        @Test
        public void case01() {
            LOGGER.debug("ReflectionAccessTest");
        }

    }
}

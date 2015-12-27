package jp.number64.javalogictest.reflection;

import jp.number64.javalogictest.reflection.ResourceContainerTest.Pattern01BasicTest;
import jp.number64.javalogictest.reflection.ResourceContainerTest.Pattern02ImitationResourceTest;

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
    Pattern01BasicTest.class,
    Pattern02ImitationResourceTest.class
})
public class ResourceContainerTest {

    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public static final class Pattern01BasicTest {
        private static final Logger LOGGER = LoggerFactory.getLogger(Pattern01BasicTest.class);

        @Test
        public void case01() {
            LOGGER.debug("BasicTest");
        }
    }

    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public static final class Pattern02ImitationResourceTest {
        private static final Logger LOGGER = LoggerFactory.getLogger(Pattern02ImitationResourceTest.class);

        @Test
        public void case01() {
            LOGGER.debug("ImitationResourceTest");
        }

    }


}

package jp.number64.javalogictest.reflection;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import jp.number64.javalogictest.reflection.PrivateResourceTest.Pattern01PrepareMessagesTest;
import jp.number64.javalogictest.reflection.PrivateResourceTest.Pattern02PrepareMessagesTest;
import jp.number64.javalogictest.reflection.PrivateResourceTest.Pattern03PrepareMessagesTest;
import jp.number64.javalogictest.reflection.PrivateResourceTest.Pattern04PrepareMessagesTest;
import jp.number64.javalogictest.reflection.PrivateResourceTest.Pattern06OutputMessageTest;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Suite.class)
@SuiteClasses({
    Pattern01PrepareMessagesTest.class,
    Pattern02PrepareMessagesTest.class,
    Pattern03PrepareMessagesTest.class,
    Pattern04PrepareMessagesTest.class,
    Pattern06OutputMessageTest.class
})
public class PrivateResourceTest {

    public static final String START_LOG = "** START: {}";

    /**
     *
     */
    public static final class Pattern01PrepareMessagesTest {
        private static final Logger LOGGER = LoggerFactory.getLogger(Pattern01PrepareMessagesTest.class);

        @Test(expected = NullPointerException.class)
        public void case01NullCheckTest() {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));

            PrivateResource target = new PrivateResource();
            target.setBaseMessages(null);
            target.prepareMessages("a", "b");
        }
    }

    /**
     *
     */
    public static final class Pattern02PrepareMessagesTest {
        private static final Logger LOGGER = LoggerFactory.getLogger(Pattern02PrepareMessagesTest.class);

        @Test(expected = IndexOutOfBoundsException.class)
        public void case01SizeCheckZero() {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));

            PrivateResource target = new PrivateResource();
            List<String> dummy = new ArrayList<>();
            target.setBaseMessages(dummy);
            target.prepareMessages("a", "b");
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void case02SizeCheckOne() {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));

            PrivateResource target = new PrivateResource();
            List<String> dummy = new ArrayList<>();
            dummy.add("value1");
            target.setBaseMessages(dummy);
            target.prepareMessages("a", "b");
        }
    }

    /**
     *
     */
    public static final class Pattern03PrepareMessagesTest {
        private static final Logger LOGGER = LoggerFactory.getLogger(Pattern03PrepareMessagesTest.class);

        @Rule
        public ExpectedException thrown = ExpectedException.none();

        @Test
        public void case01ThresholdTwo() {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));

            PrivateResource target = new PrivateResource();
            List<String> dummy = new ArrayList<>();
            dummy.add("value1");
            dummy.add("value2");
            target.setBaseMessages(dummy);
            target.prepareMessages("a", "b");
        }
    }

    /**
     *
     */
    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public static final class Pattern04PrepareMessagesTest {
        private static final Logger LOGGER = LoggerFactory.getLogger(Pattern01PrepareMessagesTest.class);

        @Test
        public void case01BaseReplaced() {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));

            PrivateResource target = new PrivateResource();
            List<String> dummy = new ArrayList<>();
            dummy.add("a");
            dummy.add("b");
            target.setBaseMessages(dummy);
            target.prepareMessages("a", "c");
            assertEquals("c", dummy.get(0));
            assertEquals("b", dummy.get(1));
        }

        @Test
        public void case02AdditionalReplaced() {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));

            PrivateResource target = new PrivateResource();
            List<String> dummy = new ArrayList<>();
            dummy.add("b");
            dummy.add("a");
            target.setBaseMessages(dummy);
            target.prepareMessages("a", "c");
            assertEquals("b", dummy.get(0));
            assertEquals("c", dummy.get(1));
        }

        @Test
        public void case03LongWordReplaced() {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));

            PrivateResource target = new PrivateResource();
            List<String> dummy = new ArrayList<>();
            dummy.add("aiueo");
            dummy.add("ueaiu");
            target.setBaseMessages(dummy);
            target.prepareMessages("ue", "UE");
            assertEquals("aiUEo", dummy.get(0));
            assertEquals("UEaiu", dummy.get(1));
        }

        @Test
        public void case04TwoWordsReplaced() {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));

            PrivateResource target = new PrivateResource();
            List<String> dummy = new ArrayList<>();
            dummy.add("aiueoueai");
            dummy.add("uexue");
            target.setBaseMessages(dummy);
            target.prepareMessages("ue", "UE");
            assertEquals("aiUEoUEai", dummy.get(0));
            assertEquals("UExUE", dummy.get(1));
        }
    }

    /**
     *
     */
    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public static final class Pattern06OutputMessageTest {
        private static final Logger LOGGER = LoggerFactory.getLogger(Pattern06OutputMessageTest.class);

        @Test
        public void case01direct() {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));

            PrivateResource target = new PrivateResource();
            List<String> dummy = new ArrayList<>();
            dummy.add("012");
            dummy.add("abc");
            target.setBaseMessages(dummy);
            assertEquals("012 abc", target.outputMessages());
        }

        @Test
        public void case02replaced() {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));

            PrivateResource target = new PrivateResource();
            List<String> dummy = new ArrayList<>();
            dummy.add("aiueo");
            dummy.add("ueoia");
            target.setBaseMessages(dummy);
            target.prepareMessages("ue", "UE");
            assertEquals("aiUEo UEoia",target.outputMessages());
        }
    }

    /** common */
    public static String getMethodName(final Throwable thisMethod) {
        StackTraceElement stackTrace = thisMethod.getStackTrace()[0];
        return stackTrace.getMethodName();
        //  return stackTrace.getClassName().
        //      substring(stackTrace.getClassName().lastIndexOf("$") + 1) + "#" + stackTrace.getMethodName();
    }
}

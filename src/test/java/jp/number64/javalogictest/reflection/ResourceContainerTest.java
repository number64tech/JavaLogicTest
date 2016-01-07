package jp.number64.javalogictest.reflection;


import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import jp.number64.javalogictest.reflection.ResourceContainerTest.Pattern01PublicAccessTest;
import jp.number64.javalogictest.reflection.ResourceContainerTest.Pattern02PrivateConstructorTest;
import jp.number64.javalogictest.reflection.ResourceContainerTest.Pattern03ResourceImitationTest;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
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
    Pattern02PrivateConstructorTest.class,
    Pattern03ResourceImitationTest.class
})
public class ResourceContainerTest {
    private static final String START_LOG = "** START: {}";

    private static final String TEST_BASE_MESSAGE = "abcdefg";
    private static final String TEST_REPLACE_TARGET = "bcd";
    private static final String TEST_NEW_WORD = "123";

    /**
     * This class tests to access "private static final String" field, and to use its value.
     */
    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public static final class Pattern01PublicAccessTest {
        private static final Logger LOGGER = LoggerFactory.getLogger(Pattern01PublicAccessTest.class);

        @Test
        public void case01DoSomething() {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));

            ResourceContainer target = new ResourceContainer(TEST_BASE_MESSAGE, TEST_REPLACE_TARGET, TEST_NEW_WORD);
            String result = target.doSomething();

            String expected = TEST_BASE_MESSAGE.replace(TEST_REPLACE_TARGET, TEST_NEW_WORD);
            Class<ResourceContainer> clazz = ResourceContainer.class;
            Field secretField = null;
            String secretMessage = null;
            try {
                secretField = clazz.getDeclaredField("SECRET_MESSAGE");
                secretField.setAccessible(true);
                secretMessage = (String)secretField.get(null);
            } catch (ReflectiveOperationException e) {
                LOGGER.debug("** an exception occured. ", e);
                fail();
            }
            LOGGER.debug("** result: {}", result);
            assertTrue(result.startsWith(expected));
            assertEquals(2, result.split(" ").length);
            assertTrue(result.endsWith(secretMessage));
        }

        /** CAUTION: Field#set() can't change "final" field value. (can change accessible, but value will not change) */
        @Test
        public void case02DoStatic() {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));

            final String replacementSecretMessage = "PUBLIC";
            final String cutMessage = "UBLI";

            Class<ResourceContainer> clazz = ResourceContainer.class;
            try {
                //
                Field secretField = clazz.getDeclaredField("SECRET_MESSAGE");
                secretField.setAccessible(true);
                secretField.set(null, replacementSecretMessage);
                //
                Field typeReverse = clazz.getDeclaredField("ENCODE_TYPE_REV");
                typeReverse.setAccessible(true);
                String keywordReverse = (String)typeReverse.get(null);
                Field typeCut = clazz.getDeclaredField("ENCODE_TYPE_CUT");
                typeCut.setAccessible(true);
                String keywordCut = (String)typeCut.get(null);
                //
                Method privateStaticMethod = clazz.getDeclaredMethod("doPrivateStaticEncode", String.class); // no parameter
                privateStaticMethod.setAccessible(true);

                //
                String resultReverse = (String)privateStaticMethod.invoke(null, keywordReverse);
                assertEquals(StringUtils.reverse(replacementSecretMessage), resultReverse);
                //
                String resultCut = (String)privateStaticMethod.invoke(null, keywordCut);
                assertEquals(cutMessage, resultCut);
                //
                String resultOther = (String)privateStaticMethod.invoke(null, "xxx");
                assertEquals("", resultOther);
            } catch (ReflectiveOperationException e) {
                LOGGER.debug("** an exception occured. ", e);
                fail();
            }
        }
    }

    /**
     *
     */
    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public static final class Pattern02PrivateConstructorTest {
        private static final Logger LOGGER = LoggerFactory.getLogger(Pattern02PrivateConstructorTest.class);

        @Test
        public void case01PrivateConstructor() {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));

            final int replacementSecretNumber = 6400;

            Class<ResourceContainer> clazz = ResourceContainer.class;
            try {
                Constructor<ResourceContainer> target =
                    clazz.getDeclaredConstructor(String.class, String.class, String.class, int.class);
                target.setAccessible(true);
                ResourceContainer instance =
                    target.newInstance(TEST_BASE_MESSAGE, TEST_REPLACE_TARGET, TEST_NEW_WORD, replacementSecretNumber); //autoboxing
                //
                Field secretNumber = clazz.getDeclaredField("secretNumber");
                Field baseMessage = clazz.getDeclaredField("baseMessage");
                Field replaceTarget = clazz.getDeclaredField("replaceTarget");
                Field newWord = clazz.getDeclaredField("newWord");
                secretNumber.setAccessible(true);
                baseMessage.setAccessible(true);
                replaceTarget.setAccessible(true);
                newWord.setAccessible(true);
                //
                assertEquals(replacementSecretNumber, secretNumber.getInt(instance));
                assertEquals(TEST_BASE_MESSAGE, (String)baseMessage.get(instance));
                assertEquals(TEST_REPLACE_TARGET, (String)replaceTarget.get(instance));
                assertEquals(TEST_NEW_WORD, (String)newWord.get(instance));
            } catch (ReflectiveOperationException e) {
                LOGGER.debug("** an exception occured. ", e);
                fail();
            }
        }
    }

    /**
     *
     */
    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public static final class Pattern03ResourceImitationTest implements PrivateResourceInterface {
        private static final Logger LOGGER = LoggerFactory.getLogger(Pattern03ResourceImitationTest.class);

        //private static final String CASE01_KEYWORD = "case01";
        //private static final String CASE02_KEYWORD = "case02";
        //private static final String CASE03_KEYWORD = "case03";
        //private static final String CASE04_KEYWORD = "case04";

        List<String> case01BaseMessages = null;
        List<String> case02BaseMessages = null;

        private ResourceContainer container = null;

        @Before
        public void doBeforeTest() {
            container = new ResourceContainer(TEST_BASE_MESSAGE, TEST_REPLACE_TARGET, TEST_NEW_WORD);




        }

        @Test
        public void case01SetBaseMessages() {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));
            container.doSomething();
        }

        @Override
        public void setBaseMessages(List<String> baseMessages) {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));
            // TODO 自動生成されたメソッド・スタブ
        }

        @Test
        public void case02GetBaseMessages() {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));

        }

        @Override
        public List<String> getBaseMessages() {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));
            // TODO 自動生成されたメソッド・スタブ
            return null;
        }

        @Test
        public void case03PrepareMessages() {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));

        }

        @Override
        public void prepareMessages(String replaceTarget, String replacedWord) {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));
            // TODO 自動生成されたメソッド・スタブ

        }

        @Test
        public void case04OutputMessage() {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));

        }

        @Override
        public String outputMessages() {
            LOGGER.debug(START_LOG, getMethodName(new Throwable()));
            // TODO 自動生成されたメソッド・スタブ
            return null;
        }



    }










    /** common */
    public static String getMethodName(final Throwable thisMethod) {
        return thisMethod.getStackTrace()[0].getMethodName();
    }
}

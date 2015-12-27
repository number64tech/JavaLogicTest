package jp.number64.javalogictest.reflection;

import java.util.List;

public interface PrivateResourceInterface {

    public void setBaseMessages(List<String> baseMessages);

    public List<String> getBaseMessages();

    public void prepareMessages(String replaceTarget, String replacedWord);

    public String outputMessages();

}

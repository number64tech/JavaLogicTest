package jp.number64.javalogictest.innerclass;

import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublicOutputHandlerImpl implements PublicOutputHandler {
    private static Logger LOGGER = LoggerFactory.getLogger(PublicOutputHandlerImpl.class);

    @Override
    public void createOutput(InnerOutputHandler handler) {
        OutputStream stream = System.out;

        try {
            handler.handle(stream);
        } catch (final RuntimeException e) {
            LOGGER.error("ERROR: {}", e);
        }
    }
}

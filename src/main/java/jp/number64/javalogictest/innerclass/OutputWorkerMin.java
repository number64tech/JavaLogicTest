package jp.number64.javalogictest.innerclass;

import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OutputWorkerMin extends OutputWorkerBase {
    private static Logger LOGGER = LoggerFactory.getLogger(OutputWorkerMin.class);

    PublicOutputHandler outputHandler = new PublicOutputHandlerImpl();

    @Override
    protected PublicOutputHandler getPublicHandler() {
        return outputHandler;
    };

    @Override
    public void onExecute(OutputStream stream) {
        try {
            stream.write(Byte.MIN_VALUE);
        } catch (IOException e) {
            LOGGER.error("ERROR: {}", e);
        }
    }

}

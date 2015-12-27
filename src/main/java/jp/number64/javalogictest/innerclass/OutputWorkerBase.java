package jp.number64.javalogictest.innerclass;

import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class OutputWorkerBase {
    private static Logger LOGGER = LoggerFactory.getLogger(OutputWorkerBase.class);

    /**
     * 実行処理
     * @param stream 出力ストリーム
     */
    public abstract void onExecute(final OutputStream stream);

    protected abstract PublicOutputHandler getPublicHandler();

    public void execute() {
        try {
            getPublicHandler().createOutput(new InnerOutputHandler() {
                @Override
                public void handle(final OutputStream stream) {
                    onExecute(stream);
                }
            });
        } catch (final RuntimeException e) {
            LOGGER.error("ERROR: {}", e);
        }
    }
}

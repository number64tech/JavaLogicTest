package jp.number64.javalogictest.innerclass;

import java.io.OutputStream;

public interface InnerOutputHandler {

    /**
     * 出力実行
     * @param stream 出力ストリーム
     */
    public void handle(OutputStream stream);

}

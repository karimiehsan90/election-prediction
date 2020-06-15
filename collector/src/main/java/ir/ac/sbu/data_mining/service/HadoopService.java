package ir.ac.sbu.data_mining.service;

import ir.ac.sbu.data_mining.dao.HadoopDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class HadoopService implements Runnable {
    private Logger logger = LoggerFactory.getLogger("cli");
    private BlockingQueue<String> hadoopDatas;
    private AtomicBoolean closed;
    private HadoopDAO hadoopDAO;
    private int count = 0;

    public HadoopService(BlockingQueue<String> hadoopDatas, AtomicBoolean closed, HadoopDAO hadoopDAO) {
        this.hadoopDatas = hadoopDatas;
        this.closed = closed;
        this.hadoopDAO = hadoopDAO;
    }

    @Override
    public void run() {
        try {
            hadoopDAO.initHadoop();
            while (!closed.get()) {
                try {
                    String line = hadoopDatas.take();
                    hadoopDAO.writeLine(line);
                    count++;
                    if (count % 1000 == 0) {
                        hadoopDAO.flush();
                        logger.info("{} tweets wrote to hadoop", count);
                    }
                } catch (InterruptedException ex) {

                }
            }
        } catch( Exception ex) {

        }
    }
}

package Server.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Logger {
    private static final String LOG_DIR = "/logs/";

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final Map<String, Logger> instances = new HashMap<>();

    private final String logFileName;

    private Logger(String logFileName) {
        this.logFileName = logFileName;
    }

    public synchronized static Logger getInstance() {
        String currentDate = DATE_FORMAT.format(new Date());
        if (!instances.containsKey(currentDate)) {
            instances.put(currentDate, new Logger(LOG_DIR + currentDate + ".log"));
        }
        return instances.get(currentDate);
    }

    public synchronized void log(String text) throws RemoteException {
        System.out.println("Log file path: " + logFileName);
        System.out.print(text); // Log to console
        writeToFile(text+"\n"); // Log to file
    }

    private void writeToFile(String logEntry) {

        try {
            FileWriter fileWriter = new FileWriter(logFileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(logEntry);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

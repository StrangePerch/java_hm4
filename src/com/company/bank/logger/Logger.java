package com.company.bank.logger;

import java.util.LinkedList;

public class Logger {
    private final LinkedList<String> info = new LinkedList<>();
    private final LinkedList<String> warnings = new LinkedList<>();
    private final LinkedList<String> errors = new LinkedList<>();
    public void LogInfo(String log)
    {
        info.add(log);
    }
    public void LogWarning(String log)
    {
        warnings.add(log);
    }
    public void LogError(String log)
    {
        errors.add(log);
    }
    public String[] getInfo()
    {
        return info.toArray(new String[0]);
    }
    public String[] getWarnings()
    {
        return warnings.toArray(new String[0]);
    }
    public String[] getErrors()
    {
        return errors.toArray(new String[0]);
    }
}

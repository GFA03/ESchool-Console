package org.example.services;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class AuditService {

    private final String filePath;

    public AuditService(String filePath) {
        this.filePath = filePath;
    }

    private void writeAuditLog(String operation, String entity, String details) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            LocalDateTime timestamp = LocalDateTime.now();
            writer.println(timestamp + "," + operation + "," + entity + "," + details);
        } catch (IOException e) {
            System.err.println("Error writing to audit log file: " + e.getMessage());
        }
    }

    public void logAdd(String entity, String details) {
        writeAuditLog("ADD", entity, details);
    }

    public void logGet(String entity, String details) {
        writeAuditLog("GET", entity, details);
    }

    public void logUpdate(String entity, String details) {
        writeAuditLog("UPDATE", entity, details);
    }

    public void logDelete(String entity, String details) {
        writeAuditLog("DELETE", entity, details);
    }
}

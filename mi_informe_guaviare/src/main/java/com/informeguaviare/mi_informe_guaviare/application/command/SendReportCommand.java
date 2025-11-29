package com.informeguaviare.mi_informe_guaviare.application.command;


import java.util.UUID;

public class SendReportCommand {

    private final UUID reportId;

    public SendReportCommand(UUID reportId) {
        this.reportId = reportId;
    }

    public UUID getReportId() {
        return reportId;
    }

}

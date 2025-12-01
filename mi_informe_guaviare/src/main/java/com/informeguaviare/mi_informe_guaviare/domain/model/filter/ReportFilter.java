package com.informeguaviare.mi_informe_guaviare.domain.model.filter;

public class ReportFilter {

    private final String status;
    private final String query;

    private final String authenticatedUserId;
    private final String authenticatedUserRole;

    public ReportFilter(String status, String query, String authenticatedUserRole, String authenticatedUserId) {
        this.status = status;
        this.query = query;
        this.authenticatedUserRole = authenticatedUserRole;
        this.authenticatedUserId = authenticatedUserId;
    }

    public boolean isBoss() {
        return "JEFE".equalsIgnoreCase(this.authenticatedUserRole);
    }

    public boolean isEmployee() {
        return "EMPLEADO".equalsIgnoreCase(this.authenticatedUserRole);
    }

    public String getStatus() {
        return status;
    }

    public String getQuery() {
        return query;
    }

    public String getAuthenticatedUserId() {
        return authenticatedUserId;
    }

    public String getAuthenticatedUserRole() {
        return authenticatedUserRole;
    }

    public boolean hasStatus() {
        return status != null && !status.isEmpty();
    }

    public boolean hasQuery() {
        return query != null && !query.isEmpty();
    }

}

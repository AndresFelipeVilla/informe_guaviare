package com.informeguaviare.mi_informe_guaviare.domain.model.filter;

public class ReportFilter {

    private final String status;
    private final String query;

    private final String authenticatedUserId;
    private final String authenticatedUserRole;

    private int page;
    private int size;

    public ReportFilter(String status, String query, String authenticatedUserRole, String authenticatedUserId, int page,
            int size) {
        this.status = status;
        this.query = query;
        this.authenticatedUserRole = authenticatedUserRole;
        this.authenticatedUserId = authenticatedUserId;
        this.page = (page < 0) ? 0 : page;
        this.size = (size < 1) ? 10 : size;
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

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public boolean hasStatus() {
        return status != null && !status.isEmpty();
    }

    public boolean hasQuery() {
        return query != null && !query.isEmpty();
    }

}

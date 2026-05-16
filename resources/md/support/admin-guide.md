# Administrator Guide

This page surveys the administrator-facing tooling in uPortal. It's a map of what's available, not a deep operations manual — the [uPortal manual](https://github.com/uPortal-Project/uportal-project.github.io) is the reference for specific configuration tasks.

If you have just stood up uPortal locally and want to poke around, sign in as `admin` / `admin` (see the [Deployment Guide](deployment-guide.html)). The admin tools appear in the navigation once you're signed in with the administrator role.

## The Admin Dashboard

The admin dashboard is uPortal's portlet-based control surface. The main areas:

- **Portlet Manager** — register, configure, categorize, and lifecycle (publish / expire) portlets and web components. Each portlet definition lives in the portal database; the dashboard is the everyday way to manage it.
- **Permissions** — set who can do what across the portal. Permissions are evaluated against groups, not directly against users.
- **Groups** — manage the group store hierarchy. uPortal supports multiple group store implementations (PAGS, LDAP, SmartLDAP, database) configured side-by-side.
- **Tab/Column Manager** — manage the tab and layout structure of the default fragments that get applied to users by group membership.
- **Layout Fragment Admin** — own the per-group fragments that compose user layouts.
- **Statistics** — view usage data captured by the event aggregator (see below).

## Authentication and groups

uPortal does not implement authentication itself; it delegates to an external identity provider via CAS, SAML, OIDC, header-based SSO, or — for the demo — a local password file. Once a user is authenticated, group membership is computed by the configured group stores.

Configuration reference in the manual:

- [PAGS group store](https://github.com/uPortal-Project/uportal-project.github.io/blob/master/manuals/en/uportal5-manual/configure/users_groups/group_stores/pags.md)
- [SmartLDAP group store](https://github.com/uPortal-Project/uportal-project.github.io/blob/master/manuals/en/uportal5-manual/configure/users_groups/group_stores/smartldap.md)

## Layouts and fragments

uPortal layouts are computed by composing **fragments** belonging to groups the user is a member of. A typical setup ships a baseline "all users" fragment plus role-specific fragments (students, faculty, staff). Per-user customizations are stored on top of the composite.

Tooling for layouts:

- **Layout fragments** are managed in the admin dashboard
- **`dataExport` / `dataImport`** Gradle tasks in uPortal-start move layout data in and out of the portal as XML files — see the [data files docs](https://github.com/uPortal-Project/uportal-project.github.io/tree/master/manuals/en/uportal5-manual/data)

## Operational topics

- **Database** — production deployments use PostgreSQL / MySQL / Oracle / MS SQL / DB2 instead of HSQLDB. Each is covered in the [database directory](https://github.com/uPortal-Project/uportal-project.github.io/tree/master/manuals/en/uportal5-manual/database) of the manual.
- **Session replication** — for clustered deployments, see the [Session Replication AOP guide](https://github.com/uPortal-Project/uportal-project.github.io/blob/master/manuals/en/uportal5-manual/configure/session_aop/session_aop.md).
- **Security headers** — set HSTS, X-Frame-Options, and the other standard hardening headers via the [security headers config](https://github.com/uPortal-Project/uportal-project.github.io/blob/master/manuals/en/uportal5-manual/configure/security/headers.md).
- **Fronting with httpd** — terminate TLS and reverse-proxy to Tomcat per the [httpd guide](https://github.com/uPortal-Project/uportal-project.github.io/blob/master/manuals/en/uportal5-manual/tomcat/fronting-with-httpd.md).
- **Event aggregation** — uPortal records portal events (page renders, portlet executions) and aggregates them on a configurable schedule, feeding the statistics dashboards. See [Event Aggregation Development](https://github.com/uPortal-Project/uportal-project.github.io/blob/master/manuals/en/uportal5-manual/developer/events/AggregationDevelopment.md).

## Where to get help

- General admin questions: [`uportal-user`](../community/mailing-lists.html)
- Specific configuration: the relevant section of the [uPortal manual](https://github.com/uPortal-Project/uportal-project.github.io)
- Need backed-up commercial help? See [Paid Support](paid-support.html).

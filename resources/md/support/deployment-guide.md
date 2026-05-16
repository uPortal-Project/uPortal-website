# Deployment Guide

The fastest way to see uPortal running is to bring up the demo locally. This guide gets you to a live portal on `http://localhost:8080/uPortal/` in a few minutes. The same workflow is the starting point for a real institutional deployment.

For deployment topics beyond this quickstart — production database setup, fronting with httpd, session replication, monitoring, SSL configuration — see the [uPortal manual](https://github.com/uPortal-Project/uportal-project.github.io/tree/master/manuals/en/uportal5-manual).

## Prerequisites

- **Java 11** (currently). uPortal manages JDK version via [SDKMAN!](https://sdkman.io/); a `.sdkmanrc` file in each repo pins the expected version. After install, run `sdk env` in a repo directory to switch.
- **Git**.
- **A POSIX shell.** Linux and macOS are first-class. On Windows, use WSL2 or run inside a Linux container — the build relies on shell features that don't translate cleanly to PowerShell.

You don't need Tomcat, Maven, or Gradle installed globally. uPortal-start downloads and manages Tomcat inside its `.gradle/` directory, and ships with a Gradle wrapper.

## Quickstart

```bash
git clone https://github.com/uPortal-Project/uPortal-start.git
cd uPortal-start
sdk env                       # switch to the Java version pinned by .sdkmanrc
./gradlew portalInit          # one-time bootstrap: clean + DB + Tomcat install + deploy + data import
./gradlew tomcatStart         # start the portal
```

Open [http://localhost:8080/uPortal/](http://localhost:8080/uPortal/). You can sign in with any of the demo accounts:

| Username | Password | Role |
| -------- | -------- | ------------ |
| `admin`  | `admin`  | Administrator |
| `staff`  | `staff`  | Staff |
| `faculty`| `faculty`| Faculty |
| `student`| `student`| Student |

## Stopping and restarting

```bash
./gradlew tomcatStop          # stop Tomcat
./gradlew hsqlStop            # stop the embedded HSQL database
./gradlew tomcatRestart       # restart Tomcat in place
```

`portalInit` is destructive — it drops and recreates the database. After the first run, prefer `tomcatStop` / `tomcatStart` for everyday work, and use the targeted `dataInit` / `dataImport` tasks when you want to refresh portal data without losing your Tomcat install.

## Where things live

| Path | What's there |
| ---- | ------------ |
| `.gradle/tomcat/` | Tomcat installation managed by uPortal-start |
| `.gradle/tomcat/logs/` | Tomcat logs |
| `.gradle/tomcat/portal/` | Runtime portal config (overrides default properties) |
| `overlays/` | Per-portlet overlay configuration |
| `gradle.properties` | Pins for the uPortal version and every portlet version |

## Going to production

The demo is configured for local exploration. For a production deployment, you'll want at minimum:

- An external RDBMS (PostgreSQL, MySQL/MariaDB, Oracle, MS SQL, or DB2) instead of HSQLDB — see the [database configuration docs](https://github.com/uPortal-Project/uportal-project.github.io/tree/master/manuals/en/uportal5-manual/database)
- A real authentication source (CAS, SAML, OIDC) instead of the demo password file
- An HTTP front (httpd or nginx) with TLS termination
- Real layouts and entitlements imported via `dataImport`

The [Administrator Guide](admin-guide.html) and [Developer Guide](developer-guide.html) pick up from here.

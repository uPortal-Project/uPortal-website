# Developer Guide

This page orients you to development on uPortal and its related projects. If you're contributing for the first time, read the [Contributor Info and Onboarding](../community/contributor-info.html) page alongside this one — that covers how the *community* works; this covers how the *code* works.

## Repo layout

uPortal development typically involves two repos working together:

- **[uPortal](https://github.com/uPortal-Project/uPortal)** — the framework itself (~40 Gradle submodules). This is where most core changes happen.
- **[uPortal-start](https://github.com/uPortal-Project/uPortal-start)** — orchestration: pulls uPortal + portlets, manages Tomcat and HSQLDB, handles configuration and deployment. This is where you build, run, and test the portal.

Portlets and web components live in their own repos. See the full inventory at [Repositories](repositories.html).

## Toolchain

| Tool | Notes |
| ---- | ----- |
| **Java 11** | Current floor and ceiling across the fleet. Managed per-repo via SDKMAN! (`sdk env` in each repo). |
| **Gradle** | uPortal core and uPortal-start use the Gradle wrapper. No global install needed. |
| **Maven** | Most portlets are Maven-based (`mvn clean install`). A few use Gradle. |
| **Tomcat 8.5** | Runtime; downloaded and managed by uPortal-start. |
| **HSQLDB** | Default development database; replace with PostgreSQL / MySQL / Oracle / MS SQL / DB2 for production. |

The fleet currently sits on Java 11 because a dependency that fixed a CVE required it. When a future bump forces movement (e.g. Java 17 or 21), the whole fleet moves together — see the [Roadmap](../community/roadmap.html).

## Common workflows

### Build uPortal core and publish to your local Maven cache

```bash
cd uPortal/
sdk env
./gradlew install          # publishes 6.0.0-SNAPSHOT to ~/.m2/repository
```

### Deploy uPortal-start

```bash
cd uPortal-start/
sdk env
./gradlew portalInit       # full bootstrap (see Deployment Guide)
```

### Incremental redeploy after changing uPortal core

```bash
# In uPortal/
./gradlew install
# In uPortal-start/
./gradlew :overlays:uPortal:tomcatDeploy   # redeploy just the portal WAR
./gradlew tomcatRestart
```

### Run end-to-end tests

uPortal-start ships a Playwright test suite at `tests/`:

```bash
./gradlew playwrightRun       # install Chromium, lint, run all specs
./gradlew playwrightDebug     # debug mode; pair with test.only()
```

## Authoritative docs

The deep technical reference lives in the **uPortal manual** repo:
[github.com/uPortal-Project/uportal-project.github.io](https://github.com/uPortal-Project/uportal-project.github.io). Highlights:

- [Developer index](https://github.com/uPortal-Project/uportal-project.github.io/tree/master/manuals/en/uportal5-manual/developer) — API docs, release process, committer onboarding, event aggregation, session replication
- [Configuration index](https://github.com/uPortal-Project/uportal-project.github.io/tree/master/manuals/en/uportal5-manual/configure) — security headers, group stores, session AOP
- [Frontend index](https://github.com/uPortal-Project/uportal-project.github.io/tree/master/manuals/en/uportal5-manual/frontend) — rendering pipeline, skinning, web components

The manual also documents the official [Maven release process](https://github.com/uPortal-Project/uportal-project.github.io/blob/master/manuals/en/uportal5-manual/developer/maven-release-process.md) committers use to ship uPortal and its portlets through Maven Central.

## A note on the rendering pipeline

uPortal's XSLT-based rendering pipeline (in `uPortal-rendering`) is powerful but stateful and lightly documented. Changes there can silently break pages for all users. If your work touches that area, post on `uportal-dev` before you start, and pair-review with a committer who has worked in it.

## Getting unstuck

- Build trouble? Check `java -version` and your `sdk env` first — Java version mismatches cause most early failures.
- Tomcat won't start? Make sure HSQLDB is running (`./gradlew hsqlStart`).
- Stuck on a real problem? Post on [`uportal-dev`](../community/mailing-lists.html) with what you've tried and the relevant log output.

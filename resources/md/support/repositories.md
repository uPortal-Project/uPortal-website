# uPortal GitHub Repositories

The uPortal ecosystem lives across three GitHub organizations:

- [uPortal-Project](https://github.com/uPortal-Project) — the core project, actively maintained portlets and tools, and most contributors do most of their work here
- [uPortal-Contrib](https://github.com/uPortal-Contrib) — community-maintained portlets, themes, and integrations
- [uPortal-attic](https://github.com/uPortal-attic) — archived projects kept for historical reference

What follows is the active subset of [uPortal-Project](https://github.com/uPortal-Project), grouped by what they do.

## Core

- [uPortal](https://github.com/uPortal-Project/uPortal) — the portal framework itself, a multi-module Gradle build
- [uPortal-start](https://github.com/uPortal-Project/uPortal-start) — orchestration repo: pulls uPortal + portlets, manages Tomcat + HSQLDB, and is where most deployers actually build and run
- [uportal-portlet-parent](https://github.com/uPortal-Project/uportal-portlet-parent) — Maven parent POM shared by uPortal portlets
- [resource-server](https://github.com/uPortal-Project/resource-server) — efficient inclusion of static resources (CSS, JS, WebJars) in uPortal pages

## Portlets

- [AnnouncementsPortlet](https://github.com/uPortal-Project/AnnouncementsPortlet) — JSR-286 portlet for announcements
- [BookmarksPortlet](https://github.com/uPortal-Project/BookmarksPortlet) — per-user bookmark management
- [CalendarPortlet](https://github.com/uPortal-Project/CalendarPortlet) — JSR-168 calendar viewer
- [CoursesPortlet](https://github.com/uPortal-Project/CoursesPortlet) — student course information
- [FeedbackPortlet](https://github.com/uPortal-Project/FeedbackPortlet) — capture user feedback in-portal
- [JasigWidgetPortlets](https://github.com/uPortal-Project/JasigWidgetPortlets) — grab bag of small utility portlets
- [NewsReaderPortlet](https://github.com/uPortal-Project/NewsReaderPortlet) — syndicated news feed viewer
- [NotificationPortlet](https://github.com/uPortal-Project/NotificationPortlet) — JSR-286 portlet for notifications (also exposes a web-component mode and HTTP API)
- [SimpleContentPortlet](https://github.com/uPortal-Project/SimpleContentPortlet) — basic content/CMS portlet
- [WebproxyPortlet](https://github.com/uPortal-Project/WebproxyPortlet) — proxy external content into a portlet window
- [basiclti-portlet](https://github.com/uPortal-Project/basiclti-portlet) — Basic LTI client portlet
- [esup-filemanager](https://github.com/uPortal-Project/esup-filemanager) — JSR-286 file management portlet (originally from ESUP-Portail)

## Web components

- [uPortal-web-components](https://github.com/uPortal-Project/uPortal-web-components) — collection of uPortal web components and JavaScript utilities
- [navigation-web-components](https://github.com/uPortal-Project/navigation-web-components) — navigation/menu components for uPortal
- [notification-web-components](https://github.com/uPortal-Project/notification-web-components) — notification UI
- [CardWebComponents](https://github.com/uPortal-Project/CardWebComponents) — card-style content components
- [soffit-samples](https://github.com/uPortal-Project/soffit-samples) — sample widgets that demonstrate the Soffit framework

## Microservices and standalone apps

- [fbms](https://github.com/uPortal-Project/fbms) — Form Builder microservice (backend)
- [form-builder](https://github.com/uPortal-Project/form-builder) — Form Builder UI: dynamic HTML forms as a web component

## Docs and community infrastructure

- [uportal-project.github.io](https://github.com/uPortal-Project/uportal-project.github.io) — the uPortal manual: release process, configuration guides, developer reference
- [uPortal-website](https://github.com/uPortal-Project/uPortal-website) — the source for this site (Clojure + Lit, deploys to GitHub Pages)
- [uPortal-steering-cmte](https://github.com/uPortal-Project/uPortal-steering-cmte) — uPSC working docs and process
- [uPortal-events](https://github.com/uPortal-Project/uPortal-events) — community event details (Dev Days, monthly calls)

For a live, complete list see [github.com/orgs/uPortal-Project/repositories](https://github.com/orgs/uPortal-Project/repositories).

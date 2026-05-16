# uPortal Features

uPortal is a framework, not a product, so a "feature list" is a bit of a fiction — most of what your campus portal looks like comes from how *you* configure and extend uPortal, not from defaults. Still, here is the shape of what you get out of the box and what you can extend.

## Personalized layouts

Every signed-in user gets a layout composed from group-based **fragments** and per-user customizations on top. Students, faculty, staff, and other roles can land on different defaults, and individuals can rearrange, add, or remove content within the bounds the institution sets.

## Pluggable authentication

uPortal does not implement authentication itself. It delegates to whatever your institution uses:

- **CAS** — single sign-on (also an Apereo project)
- **SAML**
- **OIDC**
- **Header-based SSO** behind a reverse proxy
- **Local password file** for development and demos

Once authenticated, user attributes flow into uPortal through configurable attribute sources (LDAP, JDBC, REST).

## Group-based permissions

Permissions are evaluated against groups, never directly against users. The group store is pluggable and supports multiple implementations side-by-side:

- **PAGS** (Person Attribute Group Store) — group membership derived from user attributes
- **LDAP / SmartLDAP** — group membership from your directory
- **Database** — groups stored in the portal RDBMS

## Portlets and web components

uPortal supports two parallel content models, and both are first-class:

- **JSR-286 portlets** — the classic Java portlet API. uPortal ships with a maintained set of portlets (Announcements, Calendar, News, Bookmarks, Notifications, …) and a [parent POM](https://github.com/uPortal-Project/uportal-portlet-parent) for institutions building their own.
- **Web components** — custom HTML elements built with [Lit](https://lit.dev/). The active reference is the [form-builder](https://github.com/uPortal-Project/form-builder) project; broader libraries live in [uPortal-web-components](https://github.com/uPortal-Project/uPortal-web-components) and related repos. The community direction is toward web components as the default extension model.

## Federated search

A pluggable search aggregates results across portlets that opt in. Portlets can publish their own searchable content and respond to search requests routed by the portal.

## Theming

Each deployment defines its own skin — colors, typography, branding, layout templates. The default skin uses Bootstrap 5 + Bootstrap Icons; deployers replace it with something institution-specific. See [Skinning uPortal](https://github.com/uPortal-Project/uportal-project.github.io/blob/master/manuals/en/uportal5-manual/frontend/SKINNING_UPORTAL.md) for the mechanics.

## Analytics and event aggregation

uPortal records portal events (page renders, portlet invocations, sessions) and aggregates them on a configurable interval. The result feeds the built-in statistics dashboards and can be exported for external analytics. See the [Event Aggregation Development guide](https://github.com/uPortal-Project/uportal-project.github.io/blob/master/manuals/en/uportal5-manual/developer/events/AggregationDevelopment.md).

## Internationalization

uPortal and most of its bundled portlets are translatable. English and French are first-class in the documentation; locale files cover several more languages in the framework itself.

## Operational features

- **Clustered deployment** with optional [session replication AOP](https://github.com/uPortal-Project/uportal-project.github.io/blob/master/manuals/en/uportal5-manual/configure/session_aop/session_aop.md) to catch non-serializable session content
- **Multiple databases supported** — PostgreSQL, MySQL/MariaDB, Oracle, MS SQL, DB2, HSQLDB
- **Apache 2.0 license**, no per-seat costs

## What uPortal does *not* do

To set expectations: uPortal is a portal *framework*, not a CMS, not an LMS, not an HR/SIS, not an identity provider. It's the personalized front door that ties those things together for a logged-in user, and it provides the slot framework, security model, layout engine, and rendering pipeline that make that personalization possible.

For end-user-facing examples of what real deployments look like, see the [Gallery](gallery.html).

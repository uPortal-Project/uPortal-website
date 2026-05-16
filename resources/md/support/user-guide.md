# User Guide

Most uPortal deployments are heavily customized — different layouts, themes, portlets, and authentication flows for each institution. So a generic "user guide" for uPortal as installed at your school will only get you so far; your campus help desk is the right authority for the experience their users see.

This page walks through the **uPortal demo** instead. It's the closest thing to a canonical end-user view of the framework, and it's what you'll be looking at if you just stood up uPortal locally following the [Deployment Guide](deployment-guide.html).

## The demo

The demo ships with four pre-loaded accounts. Sign in at `http://localhost:8080/uPortal/` with any of these:

| Username | Password | What you see |
| -------- | -------- | ------------ |
| `admin`   | `admin`   | Full administrator access including the admin tools |
| `staff`   | `staff`   | Staff-oriented layout |
| `faculty` | `faculty` | Faculty-oriented layout |
| `student` | `student` | Student-oriented layout |

Each role lands on a different default layout to demonstrate uPortal's role-based content targeting.

## Things to try

- **Switch between tabs.** Each tab is a page in the layout. The demo configures different content per role.
- **Personalize your layout.** The pencil/edit affordance lets you add, remove, and rearrange portlets. Layout changes are per-user and persist across sessions.
- **Use the portlet directory.** Browse available portlets and add them to your layout. The demo includes the announcement, calendar, news, bookmarks, and notification portlets.
- **Open the search.** uPortal's federated search aggregates results across portlets that opt in.
- **Sign in as `admin` and try the admin tools.** That's where you manage portlets, layouts, groups, and permissions — covered in the [Administrator Guide](admin-guide.html).

## What the demo doesn't show

- **Single sign-on.** The demo uses a local password file. Real deployments wire uPortal to CAS, SAML, or OIDC.
- **Real data sources.** Calendar, news, and announcements show sample content. In production these read from your campus systems.
- **Custom theming.** The demo ships with the default skin. Most institutions theme uPortal to match their brand — see [Skinning uPortal](https://github.com/uPortal-Project/uportal-project.github.io/blob/master/manuals/en/uportal5-manual/frontend/SKINNING_UPORTAL.md) in the manual.

## If you're looking for help with your institution's portal

The community can't troubleshoot a customized deployment at your school — your campus help desk is your first stop. If you're trying to understand what uPortal *can* do, the [Showcase](../showcase/showcase.html) and [Features](../showcase/features.html) pages are a better starting point than the demo.

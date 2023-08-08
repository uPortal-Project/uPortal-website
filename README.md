# uPortal Website (Site Generator)

Site content and tooling for maintaining the uPortal static website.

## Using this repo

There are two workflows that can be used when managing content for the uPortal website.

The easiest workflow is when you just want to create or update content. Only `Git` is required
to commit changes to the repo and push them up to GitHub. Markdown is and HTML are broadly known,
so updating this content is simple and rarely requires site testing.

Another workflow is where you want to run a web server to confirm your changes. This workflow
requires installation of `Java` and `Clojure` to run the tooling. This approach allows you to display
the site in a browser as it will appear live. As you change content, a page refresh in your browser
will show your changes.

There is a third workflow very similar to the previous one for enhancements to the tooling. Again,
`Java` and `Clojure` are required and changes can be seen after a source file save and browser
page refresh. The only notable difference from the other workflow is that new dependencies will
require restarting the web server.

## Install Git

Installation of `Git` is the most basic command needed. Your computer should already have it installed. If not, check your package manager or download from the official site. Below are some common ways
to install `Git` in case you are not a developer. However, there are many other ways to install Git.
See the [Official Git Installing Page](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

### Install Git on Mac with Homebrew

1. `$ brew install git`

### Install Git on Windows

1. Download the latest [Git for Windows installer](https://git-for-windows.github.io/)
2. Run the installer and follow the prompts.

### Install Git on Linux

#### Debian / Ubuntu (apt-get)

1. `$ sudo apt-get update`
2. `$ sudo apt-get install git-all`

#### Fedora (dnf)

1. `$ sudo dnf install git-all`

`Git` is the only tool you need for the basic editing workflow.

## Install Java

`Java` is required for running the web server, and generating the static distribution.
The version should be 8+.

While there are official
[downloads of Java from Oracle](https://www.java.com/en/download/help/download_options.html)
many of the uPortal developers perfer to use [SDKMAN!](https://sdkman.io/install) as it supports
many different versions of Java from a variety of vendors.

## Install Clojure

`Clojure`, also, is required for running the web server, and generating the static distribution.
Actually, it is a language that sits on the `Java JVM` which is why `Java` is required.

Clojure packages now include both the language and a development environment with tools.
Installation instructions can be found on the [Official Install Clojure](https://clojure.org/guides/install_clojure) page.

## Updating content (basic workflow)

If you are focusing on content (as opposed to working on the code), you will only need to look at
the `resources/` directory. There are currently three directories: `md/`, `partials/`, and `public/`.
Each directory is processed differently.

### `md/` directory

The `md/` Markdown directory hosts the Markdown files for the website. This files will have the
layout applied and be converted to HTML. Subdirectories for the path for the URL. For example,
if you have the following `resources/md/contact/us.md` then the site will display that content
at `/contact/us.html`.

_We expect most of the content will be Markdown._

### `partials/` directory

The `partials/` directory is where HTML fragments in files live. The expectation is that the content
will be a `<div>` or other HTML markup that can be placed in a `<div>` of the layout. There should
not be any `<head>` or `<body>` tags in these files. They will have the layout applied to them,
and they will display at a URL like Markdown files. A partial in the repo at `resources/partials/contact/us.html` will be displayed at `/contact/us.html`.

### `public/` directory

The `public/` directory will have minimal processing. The expectation is that the files here will
not have the layout applied. HTML files here will be used mostly as is. There might be some light
processing, like code styling applied, but it will be kept to a minimum. CSS and JavaScript files
should reside here. Again, the directories form the basis of URL paths. An HTML file at `resources/public/contact/us.html` will display at `/contact/us.html`.

### Collision detection

You may have noticed that there is a potential for different files to end up at the same URL.
This is not good! So one of the tests will detect when files from different sections will collide
on the same URL.

## Previewing content and tooling changes live (web server workflow)

If you are more inclined to see your changes before committing them to the repo, you will want to
run the web server to see your changes live. `Java` and `Clojure` will need to be installed.
You will also need to have a clone of this repo on your local drive.

1. From a command line / shell, navigate to this repo on your drive. 
  - `$ cd <repo_directory>`
2. Start the web server and wait 30 seconds for it to start.
  - `$ clj -M:run`
3. Point your browser at `http://localhost:8090/` to see the landing page.
4. Edit or add a page as above.
5. Navigate to the page and refresh as you save your changes.
6. To stop the web server, use Ctrl+C in the command line / shell.

## Generating distribution files

Generating the static files and directories to deploy is a simple command.

- `$ clj -M:build`

This command will copy all the needed files for the website in `dist/`.

## Design Decisions

Here are some design decisions for this project:
- Use libraries instead of a tool or framework. This has already paid off when the Markdown library broke and fell out of active support. It was easy to replace it with a new Markdown library.
- Clojure for the language. It's a simple, concise language that packs a lot power in a few lines of code.
- Make it easy for non-developers to add/update pages. Markdown is well known, so non-devs can just add/modify .md files in the repo.
- Target static website (with limited JS usage). We don't want this to be an additional burden on our community to maintain or fund. Upgrading WordPress and managing addons can be a pain.
- Have modest goals of a simple marketing/info site. Do not turn the uPortal site into an app.

## References

- [Stasis](https://github.com/magnars/stasis) - the key library for developing this static website
- [Blogpost on using Stasis](https://cjohansen.no/building-static-sites-in-clojure-with-stasis) - inspiration and basis for this repo
- [Repo tag for the above blogpost](https://github.com/cjohansen/cjohansen-no/tree/blog-post) - the author captured the code for his blogpost in this tag
- [Clojure CLI Guide](https://clojure.org/guides/deps_and_cli) - if you want to know about the deps.edn
- [How to use deps.edn with Stasis](https://groups.google.com/g/clojure/c/orjvY1N_HSA) - there is a long pair of comments by Jag Gunawardana that contains his setup for Stasis that uses deps.edn and supports ClojureScript/Shadow-cljs

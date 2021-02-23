<img src="https://www.yegor256.com/images/books/elegant-objects/cactus.svg" height="100px" />

[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![Managed by Zerocracy](https://www.0crat.com/badge/C63314D6Z.svg)](https://www.0crat.com/p/C63314D6Z)
[![DevOps By Rultor.com](http://www.rultor.com/b/yegor256/eo-cactoos)](http://www.rultor.com/p/yegor256/eo-cactoos)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

[![Build Status](https://travis-ci.org/yegor256/eo-cactoos.svg?branch=master)](https://travis-ci.org/yegor256/eo-cactoos)
[![PDD status](http://www.0pdd.com/svg?name=yegor256/eo-cactoos)](http://www.0pdd.com/p?name=yegor256/eo-cactoos)
[![Hits-of-Code](https://hitsofcode.com/github/yegor256/eo-cactoos)](https://hitsofcode.com/view/github/yegor256/eo-cactoos)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/yegor256/eo-cactoos/blob/master/LICENSE.txt)
![Lines of code](https://img.shields.io/tokei/lines/github/yegor256/eo-cactoos)
[![Maven Central](https://img.shields.io/maven-central/v/org.cactoos/eo-parent.svg)](https://maven-badges.herokuapp.com/maven-central/org.cactoos/eo-parent)

Object-oriented primitives for [EOLANG](https://www.eolang.org).

This is how you list all text files in a directory recursively:

```
+alias org.eolang.io.stdout
+alias org.eolang.txt.sprintf
+alias org.cactoos.io.files

each. > @
  files
    "/tmp"
    *
      [f] (f.isDir.not > @)
      [f] (/\.txt$/.matches f > @)
  [f]
    stdout > @
      sprintf "file: %s" f
```

You are welcome to add more primitives to this lib.

## How to Contribute

Fork repository, make changes, send us a pull request.
We will review your changes and apply them to the `master` branch shortly,
provided they don't violate our quality standards. To avoid frustration,
before sending us your pull request please run full Maven build:

```bash
$ mvn clean install -Pqulice
```

You will need Maven 3.3+ and Java 8+.


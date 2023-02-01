# Building Reactive Applications with Quarkus

This repository contains a sample application that demonstrates how to implement a reactive application from top-to-bottom with Quarkus. The application is a simple employee registration services. It uses RESTEasy Reactive with JAX-RS annotations to implement the HTTP facade and Hibernate Panache for its persistence strategy. The application is built with version 2.12.3.Final of Quarkus.

The code presented in this repository is the joint work of Boris Fresow and Markus Günther. It's also showcased as part of an article series on *Building Reactive Systems with Quarkus* written by both authors and published for the German JavaMagazin. The articles are in German, but if you want to check them out, they are available both in print and online:

* [#1: Reaktive Programmierung mit Quarkus](https://entwickler.de/java/quarkus-reaktive-programmierung-java) (JavaMagazin 2/2023)
* [#2: Mutiny: intuitiv verständlich?](https://entwickler.de/java/reactive-library-mutiny-java) (JavaMagazin 3/2023)
* #3: Reaktive Anwendungen mit Quarkus entwickeln (*in Vorbereitung*)
* #4: Reaktive Systeme mit Quarkus entwickeln (*in Vorbereitung*)

If you want to learn more about Quarkus, be sure to check out its [website](https://quarkus.io/).

## Running the application

You can start the application in developer mode, which brings in all the necessary bells and whistles and enables live coding.

```shell
$ mvn compile quarkus:dev
```

## License

This work is released under the terms of the MIT license.
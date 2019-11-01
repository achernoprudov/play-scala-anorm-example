[<img src="https://img.shields.io/travis/playframework/play-scala-anorm-example.svg"/>](https://travis-ci.org/playframework/play-scala-anorm-example)

# Play Framework Scala Application

Application shows basic Play Web Application with simple UI and REST API. 

# REST API

 


##Build and run

To build application use command

    sbt dist

`dist` generates zip archive in directory `/target/universal`

From this folder you must unzip archive and run it with command:

    ./scala-play-rest-app-0.0.1-SNAPSHOT/bin/scala-play-rest-app -Dplay.crypto.secret=abcdefghijk

`play.crypto.secret` provides secure for cryptographics functions
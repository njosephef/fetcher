name := "fureteur"

version := "0.0.2"

scalaVersion := "2.11.4"

mainClass := Some("Fureteur")

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"

resolvers += "Twitter Repository" at "http://maven.twttr.com"

resolvers += "Akka Maven" at "http://akka.io/repository"

libraryDependencies <++= scalaVersion { scalaVersion =>
  Seq(
    "org.scala-lang"               % "scala-library"        % "2.11.4",
    "org.scala-lang"               % "scala-reflect"        % "2.11.4",
    "org.scala-lang"               % "scala-compiler"       % "2.11.4",
    "org.scala-lang"               % "scalap"               % "2.11.4",
    "org.scala-lang.modules"       % "scala-xml_2.11"       % "1.0.3",
    "commons-pool"                 % "commons-pool"         % "1.5.6",
    "org.slf4j"                    % "slf4j-api"            % "1.6.1",
    "org.slf4j"                    % "slf4j-log4j12"        % "1.6.1"  % "provided",
    "log4j"                        % "log4j"                % "1.2.16" % "provided",
    /*"junit"                        % "junit"                % "4.8.1"  % "test",*/
    "org.apache.httpcomponents"    % "httpcore"             % "4.1",
    "org.apache.httpcomponents"    % "httpclient"           % "4.1",
    "com.typesafe.akka"            %% "akka-actor"          % "2.3.3",
    "net.liftweb"                  %% "lift-json"           % "2.6-RC1",
    "com.rabbitmq"                 % "amqp-client"          % "2.7.1"  
  )
}


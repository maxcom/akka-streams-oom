

name := "OOM"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.11"

scalacOptions := Seq(
  "-Xfatal-warnings",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-language:postfixOps",
  "-Xlint",
  "-Yno-adapted-args",
  "-Xfuture",
  "-encoding", "utf8"
)

libraryDependencies ++= {
  // runs fine on 2.5.6
  val akkaV = "2.5.7"

  Seq(
    "com.typesafe.akka" %% "akka-stream" % akkaV,
    "com.typesafe.akka" %% "akka-actor" % akkaV
  )
}

javaOptions += "-Xmx512m"

javaOptions in (Compile, run) += "-XX:+HeapDumpOnOutOfMemoryError"

fork in run := true

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")
scalacOptions += "-target:jvm-1.8"

scalacOptions in (Test, console) ~= (_ filterNot (_ == "-Ywarn-unused-import"))

scalacOptions in (Compile, console) ~= (_ filterNot (_ == "-Ywarn-unused-import"))


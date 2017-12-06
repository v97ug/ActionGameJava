name := "ActionGameJava"

version := "0.1"


// For avoiding "unmappable character for encoding ASCII" when using `sbt assembly`
// (from: https://stackoverflow.com/a/21368574/2885946)
javacOptions in Compile ++= Seq("-encoding", "UTF-8")
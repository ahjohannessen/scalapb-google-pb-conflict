import sbt._
import sbt.Keys._
import com.trueaccord.scalapb.{ScalaPbPlugin ⇒ PB}
import com.github.os72.protocjar.Protoc._

object serialization {

  private val scalaPb = "com.trueaccord.scalapb" %% "scalapb-runtime" % "0.5.39"

  def protoSettings = PB.protobufSettings ++ Seq(

      PB.runProtoc in PB.protobufConfig := (args ⇒ runProtoc("-v300" +: args.toArray)),
    PB.flatPackage in PB.protobufConfig := true,

    unmanagedResourceDirectories in Compile +=
      baseDirectory.value / "src/main/protobuf",

    libraryDependencies ++=
      Seq(scalaPb, scalaPb % PB.protobufConfig)
  )

}
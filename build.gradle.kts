repositories {
  mavenCentral()
  maven("https://jitpack.io")
}
plugins {
  kotlin("jvm") version "1.6.20"
  id("com.palantir.graal") version "0.10.0"
}
dependencies {
  implementation("com.github.vidstige:jadb:v1.2.1")
  implementation("me.tongfei:progressbar:0.9.3")
  testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
  testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.25")
  testImplementation("io.mockk:mockk:1.12.2")
}
tasks.compileKotlin {
  kotlinOptions.jvmTarget = "17"
}
tasks.compileTestKotlin {
  kotlinOptions.jvmTarget = "17"
}
tasks.test {
  useJUnitPlatform()
}
graal {
  javaVersion("17")
  graalVersion("22.0.0.2")
  option("--no-fallback")
  option("--allow-incomplete-classpath")
  mainClass("AppKt")
  outputName(projectDir.name)
  windowsVsVarsPath("C:\\Program Files\\Microsoft Visual Studio\\2022\\Enterprise\\VC\\Auxiliary\\Build\\vcvars64.bat")
}
tasks.build {
  dependsOn(tasks.nativeImage)
}

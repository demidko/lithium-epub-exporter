import AndroidFile.Companion.loadAndroidPath
import me.tongfei.progressbar.ProgressBar


fun main() {
  val apps = loadAndroidPath("/sdcard/Android/data").listNestedFiles()
  ProgressBar.wrap(apps, "Scanning").filter(::hasEpub).let(::println)
}

fun hasEpub(file: AndroidFile): Boolean {
  val name = file.name.lowercase()
  if (setOf("epub", "book", "lithium").any(name::contains)) {
    return true
  }
  val next = file.listNestedFiles()
  if (next.isEmpty()) {
    return false
  }
  return next.any(::hasEpub)
}


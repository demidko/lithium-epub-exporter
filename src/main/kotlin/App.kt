import AndroidFile.Companion.loadAndroidPath
import me.tongfei.progressbar.ProgressBar


fun main() {
  val apps = loadAndroidPath("/sdcard/Android/data").listNestedFiles()
  ProgressBar.wrap(apps, "Scanning").filter(::hasEpub).let(::println)
}

fun hasEpub(file: AndroidFile): Boolean {
  val name = file.name.lowercase()
  val keywords = setOf("epub", "book", "lithium", "fault", "read")
  if (keywords.any(name::contains)) {
    return true
  }
  val next = file.listNestedFiles()
  if (next.isEmpty()) {
    return false
  }
  return next.any(::hasEpub)
}


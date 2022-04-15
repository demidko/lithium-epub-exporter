import AndroidFile.Companion.loadAndroidSdcard
import me.tongfei.progressbar.ProgressBar
import java.io.File


fun main() {
  val dirs = loadAndroidSdcard().listNestedFiles()
  val storage = File("epub").apply(File::mkdir)
  val progress = ProgressBar.wrap(dirs, "Scan")
  progress.forEach(storage::collectEpubRecursively)
}

fun File.collectEpubRecursively(file: AndroidFile) {
  val isEpub = file.name.lowercase().endsWith(".epub")
  if (isEpub) {
    file.copyToDirectory(this)
  } else {
    file.listNestedFiles().forEach(::collectEpubRecursively)
  }
}

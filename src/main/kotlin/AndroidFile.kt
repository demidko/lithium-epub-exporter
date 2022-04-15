import se.vidstige.jadb.JadbConnection
import se.vidstige.jadb.JadbDevice
import se.vidstige.jadb.RemoteFile
import java.io.File

class AndroidFile private constructor(
  private val device: JadbDevice,
  val name: String,
  private val path: String
) {

  fun delete() {
    device.execute("rm -rf", path)
  }

  fun copyToDirectory(directory: File) {
    val source = RemoteFile(path)
    val destination = File(directory, name)
    device.pull(source, destination)
  }

  fun listNestedFiles(): List<AndroidFile> {
    val files = device.list(path).filter { file ->
      file.path != "." && file.path != ".."
    }
    return files.map { file ->
      AndroidFile(device, file.path, "$path/${file.path}")
    }
  }

  override fun toString(): String {
    return path
  }

  companion object {

    fun loadAndroidSdcard(): AndroidFile {
      return loadAndroidPath("/sdcard")
    }

    fun loadAndroidPath(path: String): AndroidFile {
      val device = JadbConnection().anyDevice
      val name = File(path).name
      return AndroidFile(device, name, path)
    }
  }
}
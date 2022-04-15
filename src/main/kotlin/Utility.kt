import com.github.ajalt.clikt.core.CliktCommand

object Utility : CliktCommand() {
  override fun run() {
    echo("It works")
  }
}
object Hangman extends App {
println("Welcome to the word guessing game, i.e. Hangman. " +
  "You have 10 guesses. " +
  "Good luck!")
  // FIXME MAKE IT SO ALL THE TEXT APPEARS IN A NEW LINE. As now it is still in in one line

  //FIXME readline to enter the guess?

  //TODO maybe add an option to guess in different languages?
  val file_path = if (args.isEmpty) "./src/Resources/englishWords.txt" else args(0)
  var guessWords = Utilities.englishWords(file_path)

  //TODO continue with the code
}

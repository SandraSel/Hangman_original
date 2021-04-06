import scala.io.StdIn.readLine

object Hangman extends App {
println("Welcome to the word guessing game, i.e. Hangman. " +
  "You have 10 guesses. " +
  "Good luck!")
  // FIXME MAKE IT SO ALL THE TEXT APPEARS IN A NEW LINE. As now it is still in in one line

  //FIXME readline to enter the guess?

  //TODO maybe add an option to guess in different languages?
  val file_path = if (args.isEmpty) "./src/Resources/englishWords.txt" else args(0)
  var guessWords = Utilities.englishWords(file_path)

  //println(guessWords)
  var endGame = false
  var win = 0
  var loss = 0

  while (!endGame){
    readLine("Type 'Exit' to leave the game, 'New' for a new game!")

    val guessingWord = Utilities.randomWord(guessWords)
    var splitWord = Utilities.wordSplit("_" * guessingWord.toUpperCase.length)
    var guessSet : Set[Char] = Set()
    var guessCount = 10

    var newGame = false
    while (!endGame && !newGame){
      def countGuesses() : Unit {

        def


      }

    }






  }

  //TODO continue with the code
}

package Hangman

import scala.io.StdIn.readLine

object Hangman extends App {

  println("Welcome to the word guessing game - Hangman. You have 10 guesses.")

  val file = if (args.isEmpty) "./src/Resources/englishWords.txt" else args(0)
  var guessWords = Utilities.englishWords(file, Utilities.setDifficulty())

  var state = GameState()

  while (!state.end) {

    println("Type 'Exit' to leave the game, 'New' for a new game!")


    val guessingWord = Utilities.randomWord(guessWords)
    guessWords = guessWords.filterNot(_ == guessingWord)
    val splitWord = Utilities.wordSplit(guessingWord.toUpperCase)
    var wordUnderscoreGuess = Utilities.wordSplit("_" * guessingWord.length) //shows the length of the word with underscores

    var newGame = false
    while (!newGame && !state.end) {
      def checkGuesses(): Unit = {

        def printResult(message: String): Unit = {
          println("\t" + Utilities.wordJoin(splitWord) + "\n")
          println(state.summary.format(message, state.wins, state.losses)) //https://docs.scala-lang.org/overviews/core/string-interpolation.html
          //https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Formatter.html#detail
          //https://alvinalexander.com/scala/scala-string-formatting-java-string-format-method/
        }
        // if the player guesses the word correctly then if versions, else looses a move
        if (splitWord == wordUnderscoreGuess) {
          newGame = true
          state.wins += 1
          printResult("Congratulations! You won!")
        } else {
          state.guessCount match {
            case 1 =>
              newGame = true
              state.losses += 1
              printResult("Sorry, but try again! ;)")
            case _ => state.guessCount -= 1
          }
        }
      }

      val playersInput = readLine(state.formattedInput.format(Utilities.wordJoin(wordUnderscoreGuess), state.guessCount)).toUpperCase
      playersInput match {
        case "NEW" => state.guessCount = 1; checkGuesses()
        case "EXIT" => state.end = true
        case "" => Nil
        case _ =>
          val letter: List[Char] = playersInput.toList
          if ((1 < letter.length) || !state.letters.contains(letter.head)) {
            println("It is not a valid guess ->" + playersInput)
          } else if (state.guessSet.contains(letter.head)) {
            println("You already guessed this! Try again!")
          } else {
            if (splitWord.contains(letter.head)) {
              wordUnderscoreGuess = Utilities.applyGuess(letter.head, wordUnderscoreGuess, splitWord)
              state.guessCount += 1
            }
            state.guessSet = state.guessSet + letter.head
            checkGuesses()
          }
      }
    }
  }
  println("Thank you for playing guessing game - Hangman!")
}

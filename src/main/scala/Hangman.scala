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
    println("Type 'Exit' to leave the game, 'New' for a new game!")

    val guessingWord = Utilities.randomWord(guessWords)
    var splitWord = Utilities.wordSplit("_" * guessingWord.toUpperCase.length)
    var guessSet : Set[Char] = Set()
    var guessCount = 10

    var newGame = false
    while (!endGame && !newGame){
      def countGuesses(): Unit = {

        def printResult(message : String): Unit = {
          println("\t" + Utilities.wordJoin(splitWord) + "\n")
          println("Wins: %2d  Losses: %2d".format(message, win, loss)) //https://docs.scala-lang.org/overviews/core/string-interpolation.html
          //https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Formatter.html#detail
          //https://alvinalexander.com/scala/scala-string-formatting-java-string-format-method/
        }
  // if the player guesses the word correctly then if versions, else looses a move
        if (splitWord == guessingWord) {
          newGame = true
          win += 1
          printResult("Congratulations! You won!")
        } else {
          guessCount match {
            case 1 =>
              newGame = true
              loss += 1
              printResult("Sorry, but try again! ;)")
            case _ => guessCount -= 1
          }
        }
      }
      val playersInput = readLine((s"You have $guessCount left: %2d . Letter: ").format(Utilities.wordJoin(splitWord), guessCount)).toUpperCase
      playersInput match {
        case "NEW GAME" | "NEW" => guessCount = 1 ; countGuesses()  //todo check if it going to work only by pressing NEW
        case "EXIT" => endGame = true
        case "" | " " => Nil
        case _ => {
          val letter : List[Char] = playersInput.toList //todo check if need to add utilities before
          if ((1 < letter.length) || !Utilities.alphaSet.contains(letter.head)) {
            println("It is not a valid guess ->" + playersInput)
          } else if (guessSet.contains(letter.head)) {
            println ("You already guessed this! Try again!")
          } else {
            if (splitWord.contains(letter.head)) {
              splitWord = Utilities.applyGuess(letter.head, splitWord, splitWord) //TODO have to split splitWord into two parts?
              guessCount += 1
            }
            guessSet = guessSet + letter.head
            countGuesses()
          }
        }
      }
    }
  }
  println("Thank you for playing guessing game - Hangman!")
}

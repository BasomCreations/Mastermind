<h4>Team Members:</h4>
Sebastian Ascoli
<br>
Jonathan Basom
<br>
<br>

<h4> External Resources Used </h4>
<a href="https://docs.oracle.com/javase/tutorial/networking/index.html.">
Oracle's tutorial on networking
</a>
<br>
<a href="https://learning.oreilly.com/library/view/tcpip-sockets-in/9780080568782/ch02.html">
TCP/IP Sockets in Java by Michael J. Donahoo, Kenneth L. Calvert
</a>
<br>
<a href="https://stackoverflow.com/questions/1073919/how-to-convert-int-into-listinteger-in-java">
StackOverflow.com - How to convert int[] into List&lt;Integer&gt; in Java?
</a>
<br>
<a href="https://stackoverflow.com/questions/4662215/how-to-extract-a-substring-using-regex">
Stack Overflow Reference on Regex
</a>
<br>
<a href="https://puzzling.stackexchange.com/questions/546/clever-ways-to-solve-mastermind">
Stackexchange discussion on Mastermind algorithms
</a>
<br>
<a href="https://github.com/nattydredd/Mastermind-Five-Guess-Algorithm/blob/master/README.md">
GitHub Mastermind-Five-Guess-Algorithm
</a>
<br>
<a href="https://github.com/nattydredd/Mastermind-Five-Guess-Algorithm/blob/master/Five-Guess-Algorithm.cpp">
GitHub Mastermind-Five-Guess-Algorithm / Five-Guess-Algorithm.cpp
</a>
<br>
<br>
<img src="src/hw01/diagrams/topLevelUML_Game_Server.png" alt="UML Diagram 1">
<br>
<br>
<img src="src/hw01/diagrams/topLevelUML_Solvers.png" alt="UML Diagram 2">

<br>
<br>
<h4>Description</h4>
<br>
<h5>Solvers</h5>
To implement the solvers we first created an abstract class called
Solver which implemented the general behavior every solver should 
have. From this class we also inherited another abstract class called 
SmartSolver, which implemented behavior we believed all "Smart" Algorithms 
should have, such as generating a list of all possible combinations.
The solvers we implemented where the following:
<ul>
<li>Random solver: Basically guesses randomly. It would guess the
solution on an average of around 1300 moves. Its only advantage is
its speed (it can do 100000 simulations in around 35 seconds).
</li>
<li>Minimax Solver: Uses Donald Knuth's Five-Guess algorithm to
solve the game. Its main advantage is that it solves the game
in an average of around 4.5 moves, its main disadvantage is that
it is slow (doing 100 simulations takes around 9 seconds).
</li>
<li>Custom Solver: We implemented a hybrid combination of the 
previous 2 algorithms (based on an algorithm provided in Stackexcange 
discussion with the link provided above). The algorithm consists 
on making a random guesses within the set of solutions that are 
still possible (it eliminates impossible solutions based on the 
responses it gets). This algorithm solves the game in an average of
around 4.64 moves. Its main advantage is that it provides a great
average at a very high performance as it provides an average really
close to that of minimax's but it has essentially the same speed as
the random solver.
</li>
</ul>






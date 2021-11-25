# file-word-count
This program is a word counter for a chosen text file. It outputs the following:
* word count,
* average word length,
* frequency of each word length,
* most frequent word lengths.

## Setup (using Eclipse)
1. Clone the repository using: https://github.com/mdaabis/file-word-count.git
2. In Eclipse go to File --> Import --> Maven --> Existing Maven Projects --> Navigate to the directory with the cloned repository and select it --> Check the corresponding pom.xml file --> Click Finish
3. Assign the file path of the file you want to run the program on to the FILE_PATH variable.
4. Run program.

## Potential Issues During Setup
If there are many errors in the classes, this is most likely due to Eclipse trying to use the wrong JRE version. To fix this you need to change the JRE version which can be done by: right click on the project in Project Explorer --> Build Path --> Configure Build Path --> Java Compiler --> Untick "Use compliance from execution environment..." checkbox --> select 1.8 for Compiler compliance level --> Apply and Close (this should refresh the project).



## Assumptions
Assumptions made for this program are:
* Hyphenated words are counted as one word.
* Formatted numbers (e.g. 18/06/2016) are counted as one word.
* Some special character should be ignored when counting characters (`.`, `,`, `!`, `:`, `;`, `-`, `(`, `)`, `?`).
* That there is always a space separating words (e.g. at the end of a sentence, there is a full stop but also a space before the start of the next sentence).
* Average word length to be calculated using the mean (as opposed to other averages like mode and median).
* The text does not contain a wide variety of special characters outside of what you might find in average text (e.g. no characters such as `^` or `~`.


## Improvements
* Add a way of differentiating between a `/` used in a formatted date and one used between two words.
* Use regex to ignore characters we don't want to consider when counting words rather than using the cleanText(String text) method as this would reduce the number of passes we have to do over the file and would allow filtering of special characters not currently accounted for.


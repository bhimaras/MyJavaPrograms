This program will write an essay automatically! Or put less grandly, it will generate random text. 
To make the result sound more like real English, than, say just choosing random words from the dictionary, we're going to use some sample text to give us information about likely words and word sequences.

We'll call the sample text document we're using the source text. Consider generating text using the frequencies of occurrences of words in the source text to choose the next word to generate in our random text. 
E.g., if "the" occurs with probability 0.03 in the source text, then we will generate it with that probability. This could be done easily by every time just choosing a word at random from the source text. 
We call random text generated this way order-0 text. We could get something that sounds a little more like English by taking into account sequences of words from the source text. For order-1 text, once we generate a word, w, we generate the next word based on its probability of following w in the source text.

Here is an example of some order-1 text that uses My Man Jeeves by P.G. Wodehouse as the source text:

    "Unsuitable for me to what the ear of omniscience. As a topping place is a wealthy bird, so mixed up by the "Lincolnshire." I looked fine in a grey check suit, and so forth. Brainy coves. Corky when I decided that one of difficult for the Park, and settled down Washington Square--artists and I'm a lot first. This makes it wasn't long cosy chats about clothes is what the game. 

This passage sounds somewhat nonsensical.

We could generalize this, using order-k word sequences, which we'll call prefixes here. Every k-word prefix we generate is a sequence found in the source text. We generate the next word by looking at the frequency of all of the successors of this prefix in the source text.

Here is an example of order-2 text from the same source:

    "I'm a bit instead of going back and having long cosy chats about the thing with aunt. So I sent Jeeves out to find a decent apartment, and settled down for a bit of exile. I'm bound to say that New York's a topping place to be called _More American Birds_. When he has put the cat out and locked up the office for the 'Lincolnshire.'" He shook his head. "I'd rather not, sir." "But it's the straight goods. I'm going to make a hit. Meanwhile, by using the utmost tact and persuasiveness, he was going to put my shirt on him." 

We see that the order-2 text is more grammatical, although a bit off from what a person would write. 

How to run the program:

javac GenText.java

java GenText prefixLength numWords sourceFile outFile

generates numWords words of prefixLength-order text using sourceFile as the source document. 

A couple of files (bluedog, aChristmasCarol.txt & aliceInWonderland.txt) are provided along which may be used as source files.
